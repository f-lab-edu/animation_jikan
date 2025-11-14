package com.artem.animationjikan.data.mapper

import com.artem.animationjikan.data.dto.AnimeDto
import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.dto.NewsDTO
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.ReviewDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.entities.NewsEntity
import com.artem.animationjikan.domain.entities.ReviewEntity
import com.artem.animationjikan.util.enums.FilterCategory

fun RecommendationAnimationDTO.toHomeCommonEntity(type: FilterCategory): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun AnimeDto.toHomeCommonEntity(type: FilterCategory): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun MangaDTO.toHomeCommonEntity(type: FilterCategory): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun UpcomingDTO.toHomeCommonEntity(type: FilterCategory): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun CharacterDTO.toHomeCommonEntity(type: FilterCategory): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun NewsDTO.toNewsEntity(): NewsEntity {
    return NewsEntity(
        malId = this.malId,
        imageUrl = this.images.jpg.imageUrl,
        title = this.title,
        date = this.date,
        authorUsername = this.authorUsername,
        excerpt = this.excerpt,
    )
}

fun ReviewDTO.toReviewEntity(): ReviewEntity {
    return ReviewEntity(
        malId = this.malId,
        username = this.user.username,
        userProfileUrl = this.user.images.jpg.imageUrl,
        review = this.review,
        score = this.score
    )
}