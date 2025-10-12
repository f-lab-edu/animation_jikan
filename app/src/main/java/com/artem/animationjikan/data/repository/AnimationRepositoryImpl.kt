package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.presentation.model.AnimationModel

class AnimationRepositoryImpl(
    private val client: JikanApiClient
) : AnimationRepository {
    override suspend fun fetchTopAnimation(): List<AnimationModel> =
        client.getTopAnimation().entry.map {
            AnimationModel(
                id = it.malId,
                title = it.title,
                imageUrl = it.images.jpg.imageUrl
            )
        }


    override suspend fun fetchRecommendationsAnimations() {
        client.getRecommendationsAnimations()
    }

}