package com.artem.animationjikan.domain.usecase

import android.util.Log
import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun execute(): Flow<Result<List<HomeCommonEntity>>> {
        val response = characterRepository.fetchTopCharacters()
        return response.map { result ->
            Result.success(result.map {
                it.toHomeCommonEntity(FilterCategory.CHARACTER)
            })
        }.catch { error ->
            Log.e("GetTopCharacterUseCase", "execute: ${error.message}")
            emit(Result.failure(error))
        }
    }

}