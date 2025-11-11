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
import com.artem.animationjikan.domain.usecase.GetRecommendAnimationUsecase
import com.artem.animationjikan.domain.usecase.GetTopAnimationUsecase
import com.artem.animationjikan.domain.usecase.GetTopCharacterUsecase
import com.artem.animationjikan.domain.usecase.GetTopMangaUsecase
import com.artem.animationjikan.domain.usecase.GetUpcomingUsecase
import com.artem.animationjikan.domain.usecase.LikeUsecase
import com.artem.animationjikan.util.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
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
    private val recommendAnimationUsecase: GetRecommendAnimationUsecase,
    private val topAnimationUseCase: GetTopAnimationUsecase,
    private val topTopMangaUseCase: GetTopMangaUsecase,
    private val topCharacterUseCase: GetTopCharacterUsecase,
    private val upcomingUsecase: GetUpcomingUsecase,
    private val likeUseCase: LikeUsecase
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
        viewModelScope.launch {
            likeUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    likeList.value = list.map { entity -> entity.mediaId }.toList()
                }
            }
        }

        viewModelScope.launch {
            combine(recommendationAnimationList, likeList) { entities, likes ->
                val likeIds = likes.toSet()
                entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
            }.collect { updateList ->
                recommendationAnimationList.value = updateList
            }
        }

        viewModelScope.launch {
            combine(upcomingList, likeList) { entities, likes ->
                val likeIds = likes.toSet()
                entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
            }.collect { updateList ->
                upcomingList.value = updateList
            }
        }

        viewModelScope.launch {
            combine(topAnimationList, likeList) { entities, likes ->
                val likeIds = likes.toSet()
                entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
            }.collect { updateList ->
                topAnimationList.value = updateList
            }
        }

        viewModelScope.launch {
            combine(topMangaList, likeList) { entities, likes ->
                val likeIds = likes.toSet()
                entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
            }.collect { updateList ->
                topMangaList.value = updateList
            }
        }

        viewModelScope.launch {
            combine(topCharacterList, likeList) { entities, likes ->
                val likeIds = likes.toSet()
                entities.map { it.copy(likeStatus = likeIds.contains(it.id)) }
            }.collect { updateList ->
                topCharacterList.value = updateList
            }
        }

        execute()
    }

    fun execute() {
        state = ViewModelState.Loading
        val initialDelayMs = 300L

        viewModelScope.launch(Dispatchers.IO) {
            recommendAnimationUsecase.execute().collect { result ->
                result.onSuccess { list ->
                    recommendationAnimationList.value = list
                    state = ViewModelState.Success
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(initialDelayMs)
            upcomingUsecase.execute().collect { result ->
                result.onSuccess { list ->
                    upcomingList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(initialDelayMs * 2)
            topAnimationUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    topAnimationList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(initialDelayMs * 3)
            topTopMangaUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    topMangaList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(initialDelayMs * 4)
            topCharacterUseCase.execute().collect { result ->
                result.onSuccess { list ->
                    topCharacterList.value = list
                }.onFailure { error ->
                    Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                }
            }
        }
    }

    fun toggleLike(entity: HomeCommonEntity) {
        Log.d("toggleLike", "entity.likeStatus is ${entity.likeStatus}")
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
                    val message: Int = R.string.submitted_like
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
                .onFailure { error -> Log.e(TAG, "${error.message}") }
        }
    }

    fun removeLike(mediaId: Int) {
        viewModelScope.launch {
            likeUseCase.removeLike(mediaId = mediaId)
                .onSuccess {
                    val message: Int = R.string.removed_like
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
                .onFailure { error -> Log.e(TAG, "${error.message}") }
        }
    }

}