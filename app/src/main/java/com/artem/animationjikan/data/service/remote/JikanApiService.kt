package com.artem.animationjikan.data.service.remote

import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.data.dto.CharacterResponse
import com.artem.animationjikan.data.dto.MangaResponse
import com.artem.animationjikan.data.dto.RecommendationAnimationResponse
import com.artem.animationjikan.data.dto.UpcomingResponse
import retrofit2.http.GET

interface JikanApiService {
    @GET("recommendations/anime")
    suspend fun getRecommendationAnimations(): RecommendationAnimationResponse

    @GET("top/anime")
    suspend fun getTopAnimation(): AnimationResponse

    @GET("top/manga")
    suspend fun getTopManga(): MangaResponse

    @GET("top/characters")
    suspend fun getTopCharacters(): CharacterResponse

    @GET("seasons/upcoming")
    suspend fun getUpcoming(): UpcomingResponse
}