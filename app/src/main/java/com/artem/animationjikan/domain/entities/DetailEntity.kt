package com.artem.animationjikan.domain.entities

data class DetailEntity(
    val malId: Int = -1,
    val title: String = "",
    val imageUrl: String? = null,
    val synopsis: String = "",
    val score: Double = 0.0,
)