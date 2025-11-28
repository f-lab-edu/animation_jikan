package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation

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
import com.artem.animationjikan.domain.usecase.AnimationDetailUseCase
import com.artem.animationjikan.domain.usecase.LikeUseCase
import com.artem.animationjikan.domain.usecase.RecentUseCase
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val animationDetailUseCase: AnimationDetailUseCase,
    private val likeUseCase: LikeUseCase,
    private val recentUseCase: RecentUseCase,
) : ViewModel() {

    companion object {
        val TAG: String? = AnimationDetailViewModel::class.simpleName
    }

    val encodedEntityString: String? = savedStateHandle.get<String>("entityData")
    var paramEntity: HomeCommonEntity? = null
    var detailEntity by mutableStateOf(DetailEntity())
        private set

    var state by mutableStateOf(ViewModelState.Idle)
        private set

    val likeStatus = MutableStateFlow(false)

    private val _eventFlow = MutableSharedFlow<UiEvent>()

    val eventFlow = _eventFlow.asSharedFlow()


    suspend fun addRecentItem(recentEntity: RecentEntity) {
        recentUseCase.addRecent(recentEntity = recentEntity)
    }

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
            val animeId = it.id
            state = ViewModelState.Loading

            viewModelScope.launch {
                addRecentItem(
                    RecentEntity(
                        mediaId = animeId,
                        imageUrl = it.imageUrl,
                        mediaType = it.type.name
                    )
                )
            }

            detailEntity = DetailEntity()

            likeUseCase.getLikeStatus(mediaId = animeId)
                .onEach { isLiked ->
                    likeStatus.value = isLiked
                }.catch { e ->
                    Log.e(TAG, "error: $e")
                    emit(false)
                }.launchIn(viewModelScope)

            //TODO When 제거 fetchAnimationDetailInfo(animeId = animeId) 만 적용
            when (it.type) {
                FilterType.ANIMATION -> fetchAnimationDetailInfo(animeId = animeId)
                FilterType.MANGA -> fetchAnimationDetailInfo(animeId = animeId)
                FilterType.CHARACTER -> fetchAnimationDetailInfo(animeId = animeId)
                FilterType.VOICE_ACTOR -> fetchAnimationDetailInfo(animeId = animeId)
            }

        } ?: run {
            Log.e("AnimationDetailViewModel", "animeId == null")
            state = ViewModelState.Error
        }
    }

    fun fetchAnimationDetailInfo(animeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            animationDetailUseCase.getAnimationDetailInfo(id = animeId).onSuccess { detailEntity ->
                state = ViewModelState.Success
                this@AnimationDetailViewModel.detailEntity = detailEntity
            }.onFailure {
                Log.e("AnimationDetailViewModel", "onFailure")
                state = ViewModelState.Error
            }
        }
    }

    /*fun fetchManaDetailInfo(mangaId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            animationDetailUseCase.getMangaDetailInfo(id = mangaId).onSuccess {
                state = ViewModelState.Success
                this@AnimationDetailViewModel.detailEntity = detailEntity
            }.onFailure {
                Log.e("AnimationDetailViewModel", "onFailure")
                state = ViewModelState.Error
            }
        }
    }*/

    fun toggleFavorite() {
        paramEntity?.let { entity ->

            val newLikeStatus = !likeStatus.value

            likeStatus.value = newLikeStatus

            viewModelScope.launch {
                if (!newLikeStatus) {
                    likeUseCase.removeLike(entity.id)
                        .onSuccess {
                            _eventFlow.emit(UiEvent.ShowToast(R.string.removed_like))
                        }.onFailure { error ->
                            Log.e(TAG, "${error.message}")
                            likeStatus.value = true
                        }
                } else {
                    likeUseCase.addLike(
                        likeEntity = LikeEntity(
                            mediaId = entity.id,
                            imageUrl = entity.imageUrl,
                            mediaType = entity.type.name
                        )
                    ).onSuccess {
                        _eventFlow.emit(UiEvent.ShowToast(R.string.submitted_like))
                    }.onFailure { error ->
                        Log.e(TAG, "${error.message}")
                        likeStatus.value = false
                    }
                }
            }
        }
    }
}
