package com.artem.animationjikan.data.service.remote

import com.artem.animationjikan.data.dto.AnimationDetailResponseDTO
import com.artem.animationjikan.data.dto.AnimationResponseDTO
import com.artem.animationjikan.data.dto.AnimeCharacterResponse
import com.artem.animationjikan.data.dto.CharacterDetailResponseDTO
import com.artem.animationjikan.data.dto.CharacterPicturesResponseDTO
import com.artem.animationjikan.data.dto.CharacterResponseDTO
import com.artem.animationjikan.data.dto.CharacterVoidActorResponseDTO
import com.artem.animationjikan.data.dto.MangaCharacterResponseDTO
import com.artem.animationjikan.data.dto.MangaDetailResponseDTO
import com.artem.animationjikan.data.dto.MangaPictureResponse
import com.artem.animationjikan.data.dto.MangaResponseDTO
import com.artem.animationjikan.data.dto.NewsResponseDTO
import com.artem.animationjikan.data.dto.ReviewResponseDTO
import com.artem.animationjikan.data.dto.UpcomingResponseDTO
import com.artem.animationjikan.data.dto.VoiceActorResponseDTO
import javax.inject.Inject

class JikanApiClient @Inject constructor(
    private val jikanApiService: JikanApiService
) {
    suspend fun fetchRecommendationAnimations() =
        jikanApiService.getRecommendationAnimations()

    suspend fun getTopAnimation(): AnimationResponseDTO = jikanApiService.getTopAnimation()

    suspend fun getTopManga(): MangaResponseDTO = jikanApiService.getTopManga()

    suspend fun getTopCharacters(): CharacterResponseDTO = jikanApiService.getTopCharacters()

    suspend fun getUpcoming(): UpcomingResponseDTO = jikanApiService.getUpcoming()

    suspend fun getAnimeNews(id: Int): NewsResponseDTO = jikanApiService.getAnimeNews(id = id)

    suspend fun getAnimeReviews(id: Int): ReviewResponseDTO =
        jikanApiService.getAnimeReviews(id = id)

    suspend fun getAnimeCharacters(id: Int): AnimeCharacterResponse =
        jikanApiService.getAnimeCharacters(id = id)

    suspend fun getMangaPictures(id: Int): MangaPictureResponse =
        jikanApiService.getMangaPictures(id = id)

    suspend fun getMangaCharacters(id: Int): MangaCharacterResponseDTO =
        jikanApiService.getMangaCharacters(id = id)

    suspend fun getMangaReviews(id: Int): ReviewResponseDTO =
        jikanApiService.getMangaReviews(id = id)

    suspend fun getAnimeFullById(id: Int): AnimationDetailResponseDTO =
        jikanApiService.getAnimeFullById(id = id)

    suspend fun getMangaFullById(id: Int): MangaDetailResponseDTO =
        jikanApiService.getMangaFullById(id = id)

    suspend fun getCharacterFullById(id: Int): CharacterDetailResponseDTO =
        jikanApiService.getCharacterFullById(id = id)

    suspend fun getCharacterVoiceActors(id: Int): CharacterVoidActorResponseDTO =
        jikanApiService.getCharacterVoiceActors(id = id)

    suspend fun searchAnime(query: String?): AnimationResponseDTO =
        jikanApiService.searchAnime(query = query)

    suspend fun searchManga(query: String?): MangaResponseDTO =
        jikanApiService.searchManga(query = query)

    suspend fun searchCharacter(query: String?): CharacterResponseDTO =
        jikanApiService.searchCharacter(query = query)

    suspend fun searchVoiceActor(query: String?): VoiceActorResponseDTO =
        jikanApiService.searchVoiceActor(query = query)

    suspend fun getCharacterPictures(id: Int): CharacterPicturesResponseDTO =
        jikanApiService.getCharacterPictures(id = id)


}