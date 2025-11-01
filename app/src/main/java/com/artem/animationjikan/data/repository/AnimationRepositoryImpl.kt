package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.data.dto.AnimeDto
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import com.artem.animationjikan.data.dto.UpcomingResponse
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.domain.repository.AnimationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimationRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : AnimationRepository {

    override suspend fun fetchRecommendationAnimations(): Flow<Result<List<RecommendationAnimationDTO>>> =
        flow {
            val response = try {
                val result: List<RecommendationAnimationDTO> =
                    client.fetchRecommendationAnimations().data.flatMap { it.entry }
                Result.success(result)
            } catch (e: Exception) {
                Result.failure(e)
            }

            emit(response)
        }

    override suspend fun fetchTopAnimation(): Flow<Result<List<AnimeDto>>> = flow {
        val response = try {
            val response: AnimationResponse = client.getTopAnimation()
            val result = response.data
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(response)
    }


    override suspend fun fetchUpcoming(): Flow<Result<List<UpcomingDTO>>> = flow {
        val response = try {
            val response: UpcomingResponse = client.getUpcoming()
            val result = response.data
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }

        emit(response)
    }

}