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
import com.artem.animationjikan.presentation.model.AnimationModel
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


class HomeTabViewModel(
    private val animationRepository: AnimationRepository,
    private val mangaRepository: MangaRepository,
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    var list by mutableStateOf<List<AnimationModel>>(emptyList())
        private set

    fun execute() {
        Log.d("HomeTabViewModel" , "execute")
        viewModelScope.launch {
            try {
                Log.d("HomeTabViewModel" , "viewModelScope")
                val result = animationRepository.fetchTopAnimation()
                list = result

                Log.d("result" , "result is $result")
            } catch(e : Exception) {
                e.printStackTrace()
            }

        }
    }

}