package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopCharacterUsecase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun execute(): Flow<Result<List<HomeCommonEntity>>> {
        val response = characterRepository.fetchTopCharacters()
        return response.map { result ->
            result.map { dtoList ->
                dtoList.map { it.toHomeCommonEntity(FilterCategory.ANIMATION) }
            }
        }
    }

}