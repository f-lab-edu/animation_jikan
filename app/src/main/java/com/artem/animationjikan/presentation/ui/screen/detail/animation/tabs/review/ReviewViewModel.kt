package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.review

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.ReviewEntity
import com.artem.animationjikan.domain.usecase.ReviewUseCase
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewUseCase: ReviewUseCase
) : ViewModel() {

    var state by mutableStateOf(ViewModelState.Idle)

    var reviewList by mutableStateOf<List<ReviewEntity>>(emptyList())
        private set

    fun fetchReviews(malId: Int) {
        viewModelScope.launch {
            state = ViewModelState.Loading
            val result = reviewUseCase.execute(malId = malId)
            result.onSuccess { reviews ->
                reviewList = reviews
                state = ViewModelState.Success
            }.onFailure {
                state = ViewModelState.Error
            }
        }
    }
}