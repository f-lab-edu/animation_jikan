package com.artem.animationjikan.data.repository

interface AnimationRepository {
    suspend fun fetchTopAnimation()
    suspend fun fetchRecommendationsAnimations()
}