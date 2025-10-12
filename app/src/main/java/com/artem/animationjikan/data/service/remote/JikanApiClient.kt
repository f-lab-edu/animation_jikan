package com.artem.animationjikan.data.service.remote

class JikanApiClient(
    private val jikanApiService: JikanApiService
) {
    suspend fun getRecommendationsAnimations() = jikanApiService.getRecommendationsAnimations()

    suspend fun getTopAnimation() = jikanApiService.getTopAnimation()

    suspend fun getTopManga() = jikanApiService.getTopManga()

    suspend fun getTopCharacters() = jikanApiService.getTopCharacters()

}