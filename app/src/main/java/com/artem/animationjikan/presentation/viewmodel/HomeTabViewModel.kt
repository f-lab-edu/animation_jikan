package com.artem.animationjikan.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.MangaRepository
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ViewModelState {
    Idle,
    Loading,
    Success,
    Error
}

@HiltViewModel
class HomeTabViewModel @Inject constructor(
    private val animationRepository: AnimationRepository,
    //TODO mangaRepository, characterRepository 사용으로 각 API 호출 코드 적용하기
    private val mangaRepository: MangaRepository,
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    var topAnimationList by mutableStateOf<List<CommonHomeContentModel>>(emptyList())
        private set

    var topMangaList by mutableStateOf<List<CommonHomeContentModel>>(emptyList())
        private set

    var topCharacterList by mutableStateOf<List<CommonHomeContentModel>>(emptyList())
        private set


    var state by mutableStateOf(ViewModelState.Idle)
        private set

    fun execute() {
        Log.d("HomeTabViewModel", "execute")
        viewModelScope.launch {
            try {
                state = ViewModelState.Loading
                val result = animationRepository.fetchTopAnimation()
                topAnimationList = result
                state = ViewModelState.Success
            } catch (e: Exception) {
                state = ViewModelState.Error
                e.printStackTrace()
            }

        }
    }
}