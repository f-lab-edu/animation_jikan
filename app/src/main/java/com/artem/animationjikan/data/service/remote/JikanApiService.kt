package com.artem.animationjikan.data.service.remote

import com.artem.animationjikan.data.dto.AnimationResponse
import retrofit2.http.GET

interface JikanApiService {
    @GET("recommendations/anime")
    suspend fun getRecommendationsAnimations()

    @GET("top/anime")
    suspend fun getTopAnimation() : AnimationResponse

    @GET("top/manga")
    suspend fun getTopManga()

    @GET("top/characters")
    suspend fun getTopCharacters()

}