package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.presentation.model.AnimationModel
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory

class AnimationRepositoryImpl(
    private val client: JikanApiClient
) : AnimationRepository {
    override suspend fun fetchTopAnimation(): List<CommonHomeContentModel> {
        return client.getTopAnimation().data?.map { animeDto ->
            CommonHomeContentModel(
                id = animeDto.malId,
                type = FilterCategory.ANIMATION,
                imageUrl = animeDto.images?.jpg?.imageUrl ?: ""
            )
        } ?: emptyList()
    }

    override suspend fun fetchRecommendationsAnimations() {
        client.getRecommendationsAnimations()
    }

}