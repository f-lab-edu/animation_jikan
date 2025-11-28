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
import com.artem.animationjikan.data.dto.RecommendationAnimationResponseDTO
import com.artem.animationjikan.data.dto.ReviewResponseDTO
import com.artem.animationjikan.data.dto.UpcomingResponseDTO
import com.artem.animationjikan.data.dto.VoiceActorResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApiService {
    @GET("recommendations/anime")
    suspend fun getRecommendationAnimations(): RecommendationAnimationResponseDTO

    @GET("top/anime")
    suspend fun getTopAnimation(): AnimationResponseDTO

    @GET("top/manga")
    suspend fun getTopManga(): MangaResponseDTO

    @GET("top/characters")
    suspend fun getTopCharacters(): CharacterResponseDTO

    @GET("seasons/upcoming")
    suspend fun getUpcoming(): UpcomingResponseDTO

    @GET("anime/{id}/news")
    suspend fun getAnimeNews(@Path("id") id: Int): NewsResponseDTO

    @GET("anime/{id}/reviews")
    suspend fun getAnimeReviews(@Path("id") id: Int): ReviewResponseDTO

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id: Int): AnimeCharacterResponse

    @GET("manga/{id}/pictures")
    suspend fun getMangaPictures(@Path("id") id: Int): MangaPictureResponse

    @GET("manga/{id}/characters")
    suspend fun getMangaCharacters(@Path("id") id: Int): MangaCharacterResponseDTO

    @GET("manga/{id}/reviews")
    suspend fun getMangaReviews(@Path("id") id: Int): ReviewResponseDTO

    @GET("anime/{id}/full")
    suspend fun getAnimeFullById(@Path("id") id: Int): AnimationDetailResponseDTO

    @GET("manga/{id}/full")
    suspend fun getMangaFullById(@Path("id") id: Int): MangaDetailResponseDTO

    @GET("characters/{id}/full")
    suspend fun getCharacterFullById(@Path("id") id: Int): CharacterDetailResponseDTO

    @GET("characters/{id}/voices")
    suspend fun getCharacterVoiceActors(
        @Path("id") id: Int
    ): CharacterVoidActorResponseDTO

    @GET("characters/{id}/pictures")
    suspend fun getCharacterPictures(@Path("id") id: Int): CharacterPicturesResponseDTO

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String?
    ): AnimationResponseDTO

    @GET("manga")
    suspend fun searchManga(
        @Query("q") query: String?
    ): MangaResponseDTO

    @GET("characters")
    suspend fun searchCharacter(
        @Query("q") query: String?
    ): CharacterResponseDTO

    @GET("people")
    suspend fun searchVoiceActor(
        @Query("q") query: String?
    ): VoiceActorResponseDTO

}