package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character

import com.artem.animationjikan.domain.usecase.AnimationCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterUseCase: AnimationCharacterUseCase
) {

}