package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.presentation.model.AnimationModel

class AnimationRepositoryImpl(
    private val client: JikanApiClient
) : AnimationRepository {
    override suspend fun fetchTopAnimation(): List<AnimationModel> {
        val list = client.getTopAnimation()
        Log.d("fetchTopAnimation list", "list $list")
        return list.data?.map { animeDto ->
            val malId = animeDto.mal_id
            Log.d("recommendationDto.title", "${animeDto.title}")
            Log.d("recommendationDto.imageUrl", animeDto.images?.jpg?.imageUrl ?: "난데")
            Log.d("recommendationDto.smallImageUrl", animeDto.images?.jpg?.smallImageUrl ?: "난데")
            Log.d("recommendationDto.largeImageUrl", animeDto.images?.jpg?.largeImageUrl ?: "난데")
            Log.d("recommendationDto.malId", "${animeDto.mal_id}")
            AnimationModel(
                id = animeDto.mal_id,
                title = animeDto.title ?: "",
                imageUrl = animeDto.images?.jpg?.imageUrl ?: ""
            )
        } ?: emptyList()
    }


    override suspend fun fetchRecommendationsAnimations() {
        client.getRecommendationsAnimations()
    }

}