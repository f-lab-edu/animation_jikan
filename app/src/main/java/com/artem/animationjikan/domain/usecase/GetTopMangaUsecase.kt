package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.MangaRepository
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopMangaUsecase @Inject constructor(
    private val mangaRepository: MangaRepository
) {
    suspend fun execute(): Flow<Result<List<HomeCommonEntity>>> {
        val response = mangaRepository.fetchTopManga()
        return response.map { result ->
            result.map { dtoList ->
                dtoList.map { it.toHomeCommonEntity(type = FilterCategory.MANGA) }
            }
        }
    }
}