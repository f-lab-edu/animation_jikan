package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterImageUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun getCharacterPictures(id: Int): Result<List<String>> =
        characterRepository.fetchCharacterPictures(id = id).mapCatching { list ->
            list.mapNotNull { it.jpg.imageUrl }
        }
}