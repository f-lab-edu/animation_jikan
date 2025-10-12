package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient

class AnimationRepositoryImpl(
    private val client : JikanApiClient
) : AnimationRepository {
    override suspend fun fetchTopAnimation() {
        client.getTopAnimation()
    }

    override suspend fun fetchRecommendationsAnimations() {
        client.getRecommendationsAnimations()
    }

}