package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.dto.CharacterDetailDTO
import com.artem.animationjikan.data.dto.ImagesDTO
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun fetchTopCharacters(): Flow<List<CharacterDTO>>

    suspend fun searchCharacter(query: String?): Flow<Result<List<CharacterDTO>>>

    suspend fun fetchCharacterFullById(id: Int): Result<CharacterDetailDTO>

    suspend fun fetchCharacterPictures(id: Int): Result<List<ImagesDTO>>

}