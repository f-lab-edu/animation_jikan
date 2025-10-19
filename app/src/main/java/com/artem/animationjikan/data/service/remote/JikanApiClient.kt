package com.artem.animationjikan.data.service.remote

import android.util.Log
import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.presentation.model.AnimationModel

class JikanApiClient(
    private val jikanApiService: JikanApiService
) {
    suspend fun getRecommendationsAnimations() {
        jikanApiService.getRecommendationsAnimations()
    }


    suspend fun getTopAnimation(): AnimationResponse {
        val response : AnimationResponse = jikanApiService.getTopAnimation()
        Log.d("getTopAnimation", "response $response")
        return response
    }

    suspend fun getTopManga() = jikanApiService.getTopManga()

    suspend fun getTopCharacters() = jikanApiService.getTopCharacters()

}