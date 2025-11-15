package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toCharacterEntity
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import javax.inject.Inject

class AnimationCharacterUseCase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(id: Int): Result<List<AnimeCharacterEntity>> {
        return try {
            val result = animationRepository.fetchAnimeCharacters(id = id).map { characterInfo ->
                characterInfo.toCharacterEntity()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}