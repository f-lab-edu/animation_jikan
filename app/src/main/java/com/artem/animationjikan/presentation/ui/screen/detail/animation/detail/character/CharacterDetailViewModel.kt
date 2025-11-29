package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character

import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.entities.RecentEntity
import com.artem.animationjikan.domain.usecase.CharacterDetailUseCase
import com.artem.animationjikan.domain.usecase.LikeUseCase
import com.artem.animationjikan.domain.usecase.RecentUseCase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.AnimationDetailViewModel
import com.artem.animationjikan.util.ENTITY_DATA
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterDetailUseCase: CharacterDetailUseCase,
    private val likeUseCase: LikeUseCase,
    private val recentUseCase: RecentUseCase,
) : ViewModel() {

    companion object {
        val TAG: String? = CharacterDetailViewModel::class.simpleName
    }

    val encodedEntityString: String? = savedStateHandle.get<String>(ENTITY_DATA)

    var paramEntity: HomeCommonEntity? = null

    var detailEntity by mutableStateOf(DetailEntity())
        private set

    var state by mutableStateOf(ViewModelState.Idle)
        private set

    val likeStatus = MutableStateFlow(false)

    private val _eventFlow = MutableSharedFlow<UiEvent>()

    val eventFlow = _eventFlow.asSharedFlow()

    init {
        if (encodedEntityString != null) {
            try {
                val jsonString = String(
                    Base64.decode(encodedEntityString, Base64.URL_SAFE or Base64.NO_WRAP),
                    Charsets.UTF_8
                )

                paramEntity = Gson().fromJson(jsonString, HomeCommonEntity::class.java)

            } catch (e: Exception) {
                Log.e(TAG, "Error parsing JSON: ${e.message}")
            }
        }


        paramEntity?.let {
            val characterId = it.id
            state = ViewModelState.Loading

            viewModelScope.launch {
                recentUseCase.addRecent(
                    recentEntity = RecentEntity(
                        mediaId = characterId,
                        imageUrl = it.imageUrl,
                        mediaType = it.type.name,
                    )
                )
            }

            likeUseCase.getLikeStatus(mediaId = characterId)
                .onEach { isLiked ->
                    likeStatus.value = isLiked
                }.catch { e ->
                    Log.e(TAG, "error: $e")
                    emit(false)
                }.launchIn(viewModelScope)


            fetchCharacterInfo(characterId = characterId)


        } ?: run {
            state = ViewModelState.Error

        }
    }

    fun fetchCharacterInfo(characterId: Int) {
        viewModelScope.launch {
            characterDetailUseCase.getCharacterDetailInfo(id = characterId)
                .onSuccess {
                    state = ViewModelState.Success
                    this@CharacterDetailViewModel.detailEntity = it
                }.onFailure {
                    Log.e(TAG, "onFailure ${it.message}")
                    state = ViewModelState.Error
                }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (!likeStatus.value) {
                likeUseCase.addLike(
                    likeEntity = LikeEntity(
                        mediaId = detailEntity.malId,
                        imageUrl = detailEntity.imageUrl,
                        mediaType = FilterType.CHARACTER.name
                    )
                ).onSuccess {
                    _eventFlow.emit(UiEvent.ShowToast(R.string.submitted_like))
                }.onFailure { error ->
                    Log.e(AnimationDetailViewModel.Companion.TAG, "${error.message}")
                    likeStatus.value = false
                }
            } else {
                likeUseCase.removeLike(mediaId = detailEntity.malId)
            }
        }
    }

}
