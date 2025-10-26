package com.artem.animationjikan.domain.repository

import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun fetchTopCharacters(): Flow<Result<List<CharacterDTO>>>
}