package com.artem.animationjikan.data.service.remote

import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.data.dto.CharacterResponse
import com.artem.animationjikan.data.dto.MangaResponse
import com.artem.animationjikan.data.dto.UpcomingResponse
import javax.inject.Inject

class JikanApiClient @Inject constructor(
    private val jikanApiService: JikanApiService
) {
    suspend fun fetchRecommendationAnimations() =
        jikanApiService.getRecommendationAnimations()

    suspend fun getTopAnimation(): AnimationResponse = jikanApiService.getTopAnimation()

    suspend fun getTopManga(): MangaResponse = jikanApiService.getTopManga()

    suspend fun getTopCharacters(): CharacterResponse = jikanApiService.getTopCharacters()

    suspend fun getUpcoming(): UpcomingResponse = jikanApiService.getUpcoming()
}