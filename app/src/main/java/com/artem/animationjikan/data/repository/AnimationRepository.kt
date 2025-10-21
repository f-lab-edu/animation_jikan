package com.artem.animationjikan.data.repository

import com.artem.animationjikan.presentation.model.CommonHomeContentModel

interface AnimationRepository {
    suspend fun fetchTopAnimation(): List<CommonHomeContentModel>

    suspend fun fetchRecommendationsAnimations()

    suspend fun fetchUpcoming(): List<CommonHomeContentModel>
}