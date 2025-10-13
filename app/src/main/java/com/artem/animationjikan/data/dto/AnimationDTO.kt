package com.artem.animationjikan.data.dto

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class AnimationResponse(
    val pagination : PageNation,
    val data : List<AnimeDto>?
)

@Serializable
data class PageNation(
    @SerializedName("last_visible_page")
    val lastVisiblePage : Int,
    @SerializedName("has_next_page")
    val hasNextPage : Boolean,
)

@Serializable
data class AnimeDto(
    @SerializedName("mal_id")
    val mal_id: Int = -1, // JSON에 Int로 들어옵니다.
    val url: String?,
    @SerializedName("images")
    val images: ImagesDto?, // 이미지 정보는 항상 중첩됩니다.
    val title: String?,
    val synopsis: String? = null // 필요한 경우 추가
)

@Serializable
data class ImagesDto(
    @SerializedName("jpg")
    val jpg : JpgImageDto?,
    @SerializedName("webp")
    val webp : WebpImageDto?
)

@Serializable
data class JpgImageDto(
    @SerializedName("image_url")
    val imageUrl : String?,
    @SerializedName("small_image_url")
    val smallImageUrl : String?,
    @SerializedName("large_image_url")
    val largeImageUrl : String?
)

@Serializable
data class WebpImageDto(
    @SerializedName("image_url")
    val imageUrl : String?,
    @SerializedName("small_image_url")
    val smallImageUrl : String?,
    @SerializedName("large_image_url")
    val largeImageUrl : String?
)