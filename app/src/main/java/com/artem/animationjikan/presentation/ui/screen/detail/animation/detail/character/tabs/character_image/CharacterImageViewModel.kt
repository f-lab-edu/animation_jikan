package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.character.tabs.character_image

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.usecase.CharacterImageUseCase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.TabBaseViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterImageViewModel @Inject constructor(
    private val characterImageUseCase: CharacterImageUseCase
) : TabBaseViewModel<Triple<String?, String?, String?>>() {

    companion object {
        val TAG: String? = CharacterImageViewModel::class.simpleName
    }

    override val type: DetailTabs = DetailTabs.SECOND

    override fun execute(malId: Int) {
        _state.value = ViewModelState.Loading

        viewModelScope.launch {
            characterImageUseCase.getCharacterPictures(id = malId)
                .onSuccess { result ->

                    _list.value = result.chunked(3).map { chunk ->
                        Triple(
                            first = chunk.getOrElse(0) { null },
                            second = chunk.getOrElse(1) { null },
                            third = chunk.getOrElse(2) { null },
                        )
                    }

                    _state.value = ViewModelState.Success

                }.onFailure {
                    Log.e(TAG, it.message.toString())
                    _state.value = ViewModelState.Error
                }
        }
    }
}