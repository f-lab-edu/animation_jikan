package com.artem.animationjikan.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.data.repository.AnimationRepositoryImpl
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.domain.repository.MangaRepository
import com.artem.animationjikan.domain.usecase.GetRecommendAnimationUsecase
import com.artem.animationjikan.domain.usecase.GetTopAnimationUsecase
import com.artem.animationjikan.domain.usecase.GetTopCharacterUsecase
import com.artem.animationjikan.domain.usecase.GetTopMangaUsecase
import com.artem.animationjikan.domain.usecase.GetUpcomingUsecase
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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
) : ViewModel() {
    companion object {
        val TAG: String? = HomeTabViewModel::class.simpleName
        const val NO_ERROR_MESSAGE = "empty Error message"
    }

    val recommendationAnimationList = MutableStateFlow<List<CommonHomeContentModel>>(emptyList())

    val topAnimationList = MutableStateFlow<List<CommonHomeContentModel>>(emptyList())
        private set

    val topMangaList = MutableStateFlow<List<CommonHomeContentModel>>(emptyList())
        private set

    val topCharacterList = MutableStateFlow<List<CommonHomeContentModel>>(emptyList())
        private set

    val upcomingList = MutableStateFlow<List<CommonHomeContentModel>>(emptyList())
        private set


    var state by mutableStateOf(ViewModelState.Idle)
        private set

    init {
        execute()
    }

    fun execute() {
        state = ViewModelState.Loading
        val initialDelayMs = 300L

        viewModelScope.launch(Dispatchers.IO) {
            recommendAnimationUsecase.execute().collect { result ->
                result.onSuccess { list ->
                    Log.d("HomeTabViewModel", "onSuccess")
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
}