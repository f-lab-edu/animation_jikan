package com.artem.animationjikan.data.service.remote

import com.artem.animationjikan.data.dto.AnimationResponse
import com.artem.animationjikan.data.dto.AnimeCharacterResponse
import com.artem.animationjikan.data.dto.CharacterResponse
import com.artem.animationjikan.data.dto.MangaResponse
import com.artem.animationjikan.data.dto.NewsResponse
import com.artem.animationjikan.data.dto.ReviewResponse
import com.artem.animationjikan.data.dto.UpcomingResponse
import javax.inject.Inject

class JikanApiClient @Inject constructor(
    private val jikanApiService: JikanApiService
) {
    suspend fun fetchRecommendationAnimations() =
        jikanApiService.getRecommendationAnimations()

    suspend fun getTopAnimation(): AnimationResponse = jikanApiService.getTopAnimation()

    suspend fun getTopManga(): MangaResponse = jikanApiService.getTopManga()

    suspend fun getTopCharacters(): CharacterResponse = jikanApiService.getTopCharacters()

    suspend fun getUpcoming(): UpcomingResponse = jikanApiService.getUpcoming()

    suspend fun getAnimeNews(id: Int): NewsResponse = jikanApiService.getAnimeNews(id = id)

    suspend fun getAnimeReviews(id: Int): ReviewResponse = jikanApiService.getAnimeReviews(id = id)

    suspend fun getAnimeCharacters(id: Int): AnimeCharacterResponse = jikanApiService.getAnimeCharacters(id = id)


    suspend fun getMangaPictures(id: Int) = jikanApiService.getMangaPictures(id = id)

    suspend fun getMangaCharacters(id: Int) = jikanApiService.getMangaCharacters(id = id)

    suspend fun getMangaReviews(id: Int) = jikanApiService.getMangaReviews(id = id)
}