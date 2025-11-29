package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toCharacterDetailEntity
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend fun getCharacterDetailInfo(id: Int): Result<DetailEntity> {
        return characterRepository.fetchCharacterFullById(id = id).mapCatching {
            it.toCharacterDetailEntity()
        }
    }
}