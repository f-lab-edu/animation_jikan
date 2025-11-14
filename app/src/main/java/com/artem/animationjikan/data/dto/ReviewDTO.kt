package com.artem.animationjikan.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    val pagination: Pagination,
    val data: List<ReviewDTO>,
)

data class ReviewDTO(
    @SerializedName("mal_id")
    val malId: Int,
    val review: String,
    val user: UserDTO,
    val score: Int,
)

data class UserDTO(
    val username: String,
    val images: ImagesDto,
)