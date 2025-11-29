package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.review

import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.ReviewEntity
import com.artem.animationjikan.domain.usecase.ReviewUseCase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.TabBaseViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewUseCase: ReviewUseCase
) : TabBaseViewModel<ReviewEntity>() {

    override val type: DetailTabs = DetailTabs.SECOND

    override fun execute(malId: Int) {
        viewModelScope.launch {
            _state.value = ViewModelState.Loading
            val result = reviewUseCase.execute(malId = malId)
            result.onSuccess { reviews ->
                _list.value = reviews
                _state.value = ViewModelState.Success
            }.onFailure {
                _state.value = ViewModelState.Error
            }
        }
    }
}