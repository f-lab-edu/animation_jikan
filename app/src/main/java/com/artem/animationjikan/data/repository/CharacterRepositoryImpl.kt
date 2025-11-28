package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.dto.CharacterDetailDTO
import com.artem.animationjikan.data.dto.ImagesDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.util.network.retryOnRateLimit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : CharacterRepository {
    override suspend fun fetchTopCharacters(): Flow<List<CharacterDTO>> = flow {
        val result = client.getTopCharacters()
        emit(result.data)
    }.retryOnRateLimit()

    override suspend fun searchCharacter(query: String?): Flow<Result<List<CharacterDTO>>> =
        flow {
            val result = runCatching { client.searchCharacter(query = query).data }
            emit(result)
        }

    override suspend fun fetchCharacterFullById(id: Int): Result<CharacterDetailDTO> =
        runCatching { client.getCharacterFullById(id = id).data }


    override suspend fun fetchCharacterPictures(id: Int): Result<List<ImagesDTO>> =
        runCatching { client.getCharacterPictures(id = id).data }


}