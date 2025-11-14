package com.artem.animationjikan.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MangaPictureResponse(
    val data: List<ImagesDto>,
)