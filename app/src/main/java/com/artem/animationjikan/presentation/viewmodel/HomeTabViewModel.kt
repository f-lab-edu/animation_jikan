package com.artem.animationjikan.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.util.CoilUtils.result
import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.MangaRepository
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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
        viewModelScope.launch {
            try {
                state = ViewModelState.Loading

                val (animationList, mangaList, characterList) = coroutineScope {

                    val animationDeferred = async {
                        animationRepository.fetchTopAnimation()
                    }

                    val mangaDeferred = async {
                        mangaRepository.fetchTopManga()
                    }

                    val characterDeferred = async {
                        characterRepository.fetchTopCharacters()
                    }

                    awaitAll(animationDeferred, mangaDeferred, characterDeferred)

                }

                topAnimationList = animationList
                topMangaList = mangaList
                topCharacterList = characterList

                state = ViewModelState.Success
            } catch (e: Exception) {
                state = ViewModelState.Error
                e.printStackTrace()
            }

        }

    }
}