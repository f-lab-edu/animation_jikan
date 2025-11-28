package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.usecase.AnimationCharacterUseCase
import com.artem.animationjikan.domain.usecase.LikeUseCase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.TabBaseViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import com.artem.animationjikan.util.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: AnimationCharacterUseCase,
    private val likeUseCase: LikeUseCase,
) : TabBaseViewModel<AnimeCharacterEntity>() {

    companion object {
        val TAG: String? = CharacterViewModel::class.simpleName
    }

    override val type: DetailTabs = DetailTabs.THIRD

    private var likeIds: Set<Int> = emptySet()

    private var cachedList: List<AnimeCharacterEntity> = emptyList()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        likeUseCase.execute(mediaType = FilterType.CHARACTER.name)
            .onEach { result ->
                likeIds = result.map { entity -> entity.mediaId }.toSet()
                if (likeIds.isNotEmpty()) {
                    updateUiList()
                }
            }.launchIn(viewModelScope)
    }

    override fun execute(malId: Int) {
        if (_state.value == ViewModelState.Loading) return

        _state.value = ViewModelState.Loading

        viewModelScope.launch {
            characterUseCase.execute(id = malId)
                .onSuccess {
                    cachedList = it
                    updateUiList()
                    _state.value = ViewModelState.Success
                }.onFailure {
                    _state.value = ViewModelState.Error
                }
        }
    }

    private fun updateUiList() {
        val mappedList = cachedList.map { character ->
            character.copy(
                likeStatus = likeIds.contains(character.malId)
            )
        }

        _list.value = mappedList
    }

    fun toggleFavorite(entity: AnimeCharacterEntity) {
        if (!entity.likeStatus) {
            addLike(
                entity = LikeEntity(
                    mediaId = entity.malId,
                    mediaType = FilterType.CHARACTER.name,
                    imageUrl = entity.imageUrl
                )
            )
        } else {
            removeLike(mediaId = entity.malId)

        }
    }

    private fun addLike(entity: LikeEntity) {
        viewModelScope.launch {
            likeUseCase.addLike(likeEntity = entity)
                .onSuccess {
                    _eventFlow.emit(UiEvent.ShowToast(R.string.submitted_like))
                }
                .onFailure { error -> Log.e(TAG, "${error.message}") }
        }
    }

    private fun removeLike(mediaId: Int) {
        viewModelScope.launch {
            likeUseCase.removeLike(mediaId = mediaId)
                .onSuccess { _eventFlow.emit(UiEvent.ShowToast(R.string.removed_like)) }
                .onFailure { error -> Log.e(TAG, "${error.message}") }
        }
    }
}