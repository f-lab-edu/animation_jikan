package com.artem.animationjikan.data.service.remote

import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.util.network.RetrofitClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface JikanApiService {
    @GET("recommendations/anime")
    suspend fun getRecommendationsAnimations()

    @GET("top/anime")
    suspend fun getTopAnimation(): AnimationResponse

    @GET("top/manga")
    suspend fun getTopManga()

    @GET("top/characters")
    suspend fun getTopCharacters()

}

fun createJikanApiService(): JikanApiService {
    val retrofitInstance: Retrofit =
        RetrofitClient.client ?: throw IllegalStateException("RetrofitClient client is null.")
    val service: JikanApiService = retrofitInstance.create(JikanApiService::class.java)
    return service
}