package com.artem.animationjikan.presentation.ui.tab.like

import androidx.lifecycle.ViewModel
import com.artem.animationjikan.domain.usecase.LikeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeUsecase: LikeUsecase
) : ViewModel() {

    private fun getLikes() {
        //likeUsecase.getLikeOfType()
    }
}