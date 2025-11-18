package com.artem.animationjikan.domain.entities

data class ReviewEntity(
    val malId: Int,
    val username: String,
    val userProfileUrl: String?,
    val review: String,
    val score: Int,
)