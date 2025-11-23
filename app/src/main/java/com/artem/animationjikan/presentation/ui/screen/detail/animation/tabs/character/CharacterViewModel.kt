package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.character

import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity
import com.artem.animationjikan.domain.usecase.AnimationCharacterUseCase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.TabBaseViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: AnimationCharacterUseCase
) : TabBaseViewModel<AnimeCharacterEntity>() {

    override val type: DetailTabs = DetailTabs.THIRD

    override fun execute(malId: Int) {
        if (_state.value == ViewModelState.Loading) return

        _state.value = ViewModelState.Loading
        _list.value = emptyList()

        viewModelScope.launch {
            characterUseCase.execute(id = malId)
                .onSuccess {
                    _list.value = it
                    _state.value = ViewModelState.Success
                }.onFailure {
                    _state.value = ViewModelState.Error
                }
        }
    }
}