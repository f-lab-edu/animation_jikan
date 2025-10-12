package com.artem.animationjikan.data.repository

import com.artem.animationjikan.presentation.model.AnimationModel

interface AnimationRepository {
    suspend fun fetchTopAnimation() : List<AnimationModel>
    suspend fun fetchRecommendationsAnimations()
}