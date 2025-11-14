package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.repository.AnimationRepository
import javax.inject.Inject

class AnimationCharacterUseCase @Inject constructor(
    private val animationRepository: AnimationRepository
) {

    suspend fun execute() {
        animationRepository.fetchAnimeCharacters(0)
    }
}