package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toNewsEntity
import com.artem.animationjikan.domain.entities.NewsEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import javax.inject.Inject

class NewsUsecase @Inject constructor(
    private val animationRepository: AnimationRepository
) {
    suspend fun execute(id: Int): Result<List<NewsEntity>> {
        return runCatching {
            animationRepository.fetchAnimeNews(id).map { it.toNewsEntity() }
        }
    }
}