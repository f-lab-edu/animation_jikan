package com.artem.animationjikan.presentation.ui.tab.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.usecase.GetRecommendAnimationUseCase
import com.artem.animationjikan.domain.usecase.GetTopAnimationUseCase
import com.artem.animationjikan.domain.usecase.GetTopCharacterUseCase
import com.artem.animationjikan.domain.usecase.GetTopMangaUseCase
import com.artem.animationjikan.domain.usecase.GetUpcomingUseCase
import com.artem.animationjikan.domain.usecase.LikeUseCase
import com.artem.animationjikan.util.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ViewModelState {
    Idle,
    Loading,
    Success,
    Error
}

@HiltViewModel
class HomeTabViewModel @Inject constructor(
    private val recommendAnimationUseCase: GetRecommendAnimationUseCase,
    private val topAnimationUseCase: GetTopAnimationUseCase,
    private val topTopMangaUseCase: GetTopMangaUseCase,
    private val topCharacterUseCase: GetTopCharacterUseCase,
    private val upcomingUseCase: GetUpcomingUseCase,
    private val likeUseCase: LikeUseCase
) : ViewModel() {
    companion object {
        val TAG: String? = HomeTabViewModel::class.simpleName
        const val NO_ERROR_MESSAGE = "empty Error message"
    }

    private val likeList = MutableStateFlow<List<Int>>(emptyList())

    val recommendationAnimationList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    val topAnimationList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    val topMangaList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    val topCharacterList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    val upcomingList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    var state by mutableStateOf(ViewModelState.Idle)
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        likeUseCase.execute().onEach { result ->
            likeList.value = result.map { entity -> entity.mediaId }.toList()
        }.launchIn(viewModelScope)

        combine(recommendationAnimationList, likeList) { entities, likes ->
            val likeIds = likes.toSet()
            entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
        }.onEach { updateList ->
            recommendationAnimationList.value = updateList
        }.launchIn(viewModelScope)

        combine(upcomingList, likeList) { entities, likes ->
            val likeIds = likes.toSet()
            entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
        }.onEach { updateList ->
            upcomingList.value = updateList
        }.launchIn(viewModelScope)

        combine(topAnimationList, likeList) { entities, likes ->
            val likeIds = likes.toSet()
            entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
        }.onEach { updateList ->
            topAnimationList.value = updateList
        }.launchIn(viewModelScope)

        combine(topMangaList, likeList) { entities, likes ->
            val likeIds = likes.toSet()
            entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
        }.onEach { updateList ->
            topMangaList.value = updateList
        }.launchIn(viewModelScope)

        combine(topCharacterList, likeList) { entities, likes ->
            val likeIds = likes.toSet()
            entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
        }.onEach { updateList ->
            topCharacterList.value = updateList
        }.launchIn(viewModelScope)

        execute()
    }

    fun execute() {
        viewModelScope.launch {
            delay(5000L)
        }

        state = ViewModelState.Loading

        val initialDelayMs = 300L

        viewModelScope.launch(Dispatchers.IO) {

            var isError = true

            recommendAnimationUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    recommendationAnimationList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                    isError = true
                }
            }

            delay(initialDelayMs)
            upcomingUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    upcomingList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                    isError = true
                }
            }

            delay(initialDelayMs * 2)
            topAnimationUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    topAnimationList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                    isError = true
                }
            }

            delay(initialDelayMs * 3)
            topTopMangaUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    topMangaList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                    isError = true
                }
            }

            delay(initialDelayMs * 4)
            topCharacterUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    topCharacterList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                    isError = true
                }
            }

            state = if (isError) ViewModelState.Error else ViewModelState.Success
        }

    }

    fun toggleLike(entity: HomeCommonEntity) {
        if (!entity.likeStatus) {
            addLike(
                entity = LikeEntity(
                    mediaId = entity.id,
                    mediaType = entity.type.name,
                    imageUrl = entity.imageUrl
                )
            )
        } else {
            removeLike(mediaId = entity.id)
        }
    }

    fun addLike(entity: LikeEntity) {
        viewModelScope.launch {
            likeUseCase.addLike(likeEntity = entity)
                .onSuccess {
                    _eventFlow.emit(UiEvent.ShowToast(R.string.submitted_like))
                }
                .onFailure { error -> Log.e(TAG, "${error.message}") }
        }
    }

    fun removeLike(mediaId: Int) {
        viewModelScope.launch {
            likeUseCase.removeLike(mediaId = mediaId)
                .onSuccess {
                    _eventFlow.emit(UiEvent.ShowToast(R.string.removed_like))
                }
                .onFailure { error -> Log.e(TAG, "${error.message}") }
        }
    }

}