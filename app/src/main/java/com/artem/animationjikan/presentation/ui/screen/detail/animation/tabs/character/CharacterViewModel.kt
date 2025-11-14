package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.usecase.AnimationCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: AnimationCharacterUseCase
) : ViewModel() {
    companion object {
        val TAG: String? = CharacterViewModel::class.simpleName
    }

    init {
        execute()
    }


    fun execute() {
        viewModelScope.launch {
            characterUseCase.execute()
        }
    }
}