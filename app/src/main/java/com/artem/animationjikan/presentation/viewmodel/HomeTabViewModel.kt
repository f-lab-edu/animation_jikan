package com.artem.animationjikan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.data.repository.AnimationRepository
import com.artem.animationjikan.data.repository.CharacterRepository
import com.artem.animationjikan.data.repository.MangaRepository
import kotlinx.coroutines.launch

class HomeTabViewModel(
    private val animationRepository: AnimationRepository,
    private val mangaRepository: MangaRepository,
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    fun execute() {
        viewModelScope.launch {
            animationRepository.fetchTopAnimation()
        }
    }

}