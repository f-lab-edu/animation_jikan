package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity
import com.artem.animationjikan.domain.usecase.AnimationCharacterUseCase
import com.artem.animationjikan.util.enums.ViewModelState
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

    var characterList by mutableStateOf<List<AnimeCharacterEntity>>(value = emptyList())
        private set

    var state by mutableStateOf(ViewModelState.Idle)
        private set

    fun fetchAnimeCharacters(malId: Int) {
        viewModelScope.launch {
            characterList = characterUseCase.execute(id = malId)
        }
    }
}