package com.artem.animationjikan.presentation.ui.screen.detail.animation

import androidx.lifecycle.ViewModel
import com.artem.animationjikan.domain.repository.AnimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimationDetailViewModel @Inject constructor(
    private val animationRepository: AnimationRepository
) : ViewModel() {

}
