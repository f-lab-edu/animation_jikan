package com.artem.animationjikan.data.repository

import android.util.Log
import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory
import javax.inject.Inject

class AnimationRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : AnimationRepository {
    override suspend fun fetchTopAnimation(): List<CommonHomeContentModel> {
        return client.getTopAnimation().data.map { animeDto ->
            CommonHomeContentModel(
                id = animeDto.malId,
                type = FilterCategory.ANIMATION,
                imageUrl = animeDto.images.jpg.imageUrl
            )
        }
    }

    override suspend fun fetchRecommendationAnimations(): List<CommonHomeContentModel> {
        val maxItemCount = 5
        val response = client.fetchRecommendationAnimations().data
        val originList = response.flatMap { recommendationData ->
            recommendationData.entry.map { recommendationDTO ->
                CommonHomeContentModel(
                    id = recommendationDTO.malId,
                    type = FilterCategory.ANIMATION,
                    imageUrl = recommendationDTO.images.jpg.imageUrl
                )
            }
        }
        //List를 섞으며, 최대 출력을 5개로 줄인다.
        return originList.shuffled().take(maxItemCount)
    }

    override suspend fun fetchUpcoming(): List<CommonHomeContentModel> {
        return client.getUpcoming().data.map { upcomingDTO ->
            CommonHomeContentModel(
                id = upcomingDTO.malId,
                type = FilterCategory.ANIMATION,
                imageUrl = upcomingDTO.images.jpg.imageUrl
            )
        }
    }

}