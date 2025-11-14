package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.news

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.NewsEntity
import com.artem.animationjikan.domain.usecase.NewsUsecase
import com.artem.animationjikan.util.NO_ERROR_MESSAGE
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUsecase
) : ViewModel() {

    companion object {
        val TAG: String? = NewsViewModel::class.simpleName
    }

    var newsList by mutableStateOf<List<NewsEntity>>(emptyList())
        private set

    var state by mutableStateOf(ViewModelState.Idle)
        private set

    fun fetchAnimeNews(malId: Int) {
        state = ViewModelState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = newsUseCase.execute(malId)
            result.onSuccess { list ->
                newsList = list
                state = ViewModelState.Success
            }.onFailure { error ->
                Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                state = ViewModelState.Error
            }
        }
    }
}