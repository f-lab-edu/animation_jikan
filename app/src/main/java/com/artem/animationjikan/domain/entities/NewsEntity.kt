package com.artem.animationjikan.domain.entities

data class NewsEntity(
    val malId: Int,
    val title: String,
    val date: String,
    val authorUsername: String,
    val imageUrl: String?,
    val excerpt: String
)