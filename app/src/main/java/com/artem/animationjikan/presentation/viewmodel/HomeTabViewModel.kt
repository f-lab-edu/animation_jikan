package com.artem.animationjikan.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.AnimationRepositoryImpl
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.CharacterRepositoryImpl
import com.artem.animationjikan.data.repository.MangaRepository
import com.artem.animationjikan.data.repository.MangaRepositoryImpl
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.data.service.remote.JikanApiService
import com.artem.animationjikan.data.service.remote.createJikanApiService
import com.artem.animationjikan.presentation.factory.HomeTabViewModelFactory
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import kotlinx.coroutines.launch

fun createHomeTabViewModelFactory(): HomeTabViewModelFactory {
    val service: JikanApiService = createJikanApiService()
    val client = JikanApiClient(service)
    val animRepository: AnimationRepository = AnimationRepositoryImpl(client)
    val mangaRepository: MangaRepository = MangaRepositoryImpl(client)
    val characterRepository: CharacterRepository = CharacterRepositoryImpl(client)

    return HomeTabViewModelFactory(
        animationRepository = animRepository,
        mangaRepository = mangaRepository,
        characterRepository = characterRepository
    )
}

enum class ViewModelState {
    Idle,
    Loading,
    Success,
    Error
}


class HomeTabViewModel(
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