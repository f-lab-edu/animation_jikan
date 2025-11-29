package com.artem.animationjikan.data.mapper

import com.artem.animationjikan.data.dto.ActorDTO
import com.artem.animationjikan.data.dto.AnimationDetailDTO
import com.artem.animationjikan.data.dto.AnimeCharacterDTO
import com.artem.animationjikan.data.dto.AnimeDTO
import com.artem.animationjikan.data.dto.CharacterDTO
import com.artem.animationjikan.data.dto.CharacterDetailDTO
import com.artem.animationjikan.data.dto.CharacterVoiceActorDTO
import com.artem.animationjikan.data.dto.MangaDTO
import com.artem.animationjikan.data.dto.MangaDetailDTO
import com.artem.animationjikan.data.dto.NewsDTO
import com.artem.animationjikan.data.dto.RecommendationAnimationDTO
import com.artem.animationjikan.data.dto.ReviewDTO
import com.artem.animationjikan.data.dto.UpcomingDTO
import com.artem.animationjikan.data.dto.VoiceActorDTO
import com.artem.animationjikan.domain.entities.ActorEntity
import com.artem.animationjikan.domain.entities.AnimeCharacterEntity
import com.artem.animationjikan.domain.entities.CharacterVoiceActorEntity
import com.artem.animationjikan.domain.entities.DetailEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.entities.NewsEntity
import com.artem.animationjikan.domain.entities.ReviewEntity
import com.artem.animationjikan.util.enums.FilterType

fun RecommendationAnimationDTO.toHomeCommonEntity(type: FilterType): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun AnimeDTO.toHomeCommonEntity(type: FilterType): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}

fun MangaDTO.toHomeCommonEntity(type: FilterType): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}
fun CharacterDTO.toHomeCommonEntity(type: FilterType): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}
fun VoiceActorDTO.toHomeCommonEntity(type: FilterType): HomeCommonEntity {
    return HomeCommonEntity(
        id = this.malId,
        type = type,
        imageUrl = this.images.jpg.imageUrl,
    )
}
fun UpcomingDTO.toHomeCommonEntity(type: FilterType): HomeCommonEntity {
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
        score = this.score,
    )
}

fun AnimeCharacterDTO.toCharacterEntity(): AnimeCharacterEntity {
    return AnimeCharacterEntity(
        malId = this.character.malId,
        characterName = this.character.name,
        imageUrl = this.character.images.jpg.imageUrl,
        actors = this.actors.map { it.toActorEntity() },
        role = this.role,
    )
}

fun ActorDTO.toActorEntity(): ActorEntity {
    return ActorEntity(
        malId = this.person.malId,
        imageUrl = this.person.images.jpg.imageUrl,
        name = this.person.name,
        language = this.language,
    )
}

fun AnimationDetailDTO.toAnimationDetailEntity(): DetailEntity {
    return DetailEntity(
        malId = this.malId,
        title = this.title,
        imageUrl = this.images.jpg.largeImageUrl,
        synopsis = this.synopsis,
        score = this.score,
    )
}

fun MangaDetailDTO.toMangaDetailEntity(): DetailEntity {
    return DetailEntity(
        malId = this.malId,
        title = this.title,
        imageUrl = this.images.jpg.largeImageUrl,
        synopsis = this.synopsis,
        score = this.score,
    )
}

fun CharacterDetailDTO.toCharacterDetailEntity(): DetailEntity {
    return DetailEntity(
        malId = this.malId,
        title = this.title,
        imageUrl = this.images.jpg.imageUrl,
        synopsis = this.synopsis,
        score = this.favorites.toDouble(),
    )
}

fun CharacterVoiceActorDTO.toCharacterVoiceActorEntity(): CharacterVoiceActorEntity {
    return CharacterVoiceActorEntity(
        malId = this.person.malId,
        language = this.language,
        imageUrl = this.person.images.jpg.imageUrl,
        name = this.person.name,
    )
}