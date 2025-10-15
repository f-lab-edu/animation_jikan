package com.artem.animationjikan.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.MangaRepository
import com.artem.animationjikan.presentation.viewmodel.HomeTabViewModel


class HomeTabViewModelFactory(
    private val animationRepository: AnimationRepository,
    private val mangaRepository: MangaRepository,
    private val characterRepository : CharacterRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeTabViewModel::class.java)) {
            return HomeTabViewModel(
                animationRepository = animationRepository,
                mangaRepository = mangaRepository,
                characterRepository = characterRepository
            ) as T
        }
        return super.create(modelClass)
    }
}