package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.util.network.retryOnRateLimit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : CharacterRepository {
    override suspend fun fetchTopCharacters(): Flow<List<CharacterDTO>> = flow {
        val result = client.getTopCharacters()
        emit(result.data)
    }.retryOnRateLimit()
}