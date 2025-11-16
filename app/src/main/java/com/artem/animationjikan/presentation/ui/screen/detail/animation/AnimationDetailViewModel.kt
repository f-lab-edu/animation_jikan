package com.artem.animationjikan.presentation.ui.screen.detail.animation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.AnimationDetailEntity
import com.artem.animationjikan.domain.usecase.AnimationDetailUseCase
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val animationDetailUseCase: AnimationDetailUseCase
) : ViewModel() {

    companion object {
        val TAG: String? = AnimationDetailViewModel::class.simpleName
    }

    val animeId: Int? = savedStateHandle.get<String>("malId")?.toIntOrNull() ?: run {
        Log.e(TAG, "Anime ID not found in SavedStateHandle")
        null
    }

    var animationDetailEntity by mutableStateOf(AnimationDetailEntity())
        private set

    var state by mutableStateOf(ViewModelState.Idle)
        private set


    init {
        if (animeId != null) {
            Log.e("AnimationDetailViewModel","animeId != null")
            state = ViewModelState.Loading
            animationDetailEntity = AnimationDetailEntity()

            viewModelScope.launch(Dispatchers.IO) {
                animationDetailUseCase.execute(id = animeId).onSuccess {
                    state = ViewModelState.Success
                    animationDetailEntity = it
                    Log.e("AnimationDetailViewModel","ViewModelState.Success")
                }.onFailure {
                    Log.e("AnimationDetailViewModel","onFailure")
                    state = ViewModelState.Error
                }
            }
        } else {
            Log.e("AnimationDetailViewModel","animeId == null")
            state = ViewModelState.Error
        }
    }
}
