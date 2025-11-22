package com.artem.animationjikan.domain.usecase

import com.artem.animationjikan.data.mapper.toHomeCommonEntity
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.repository.AnimationRepository
import com.artem.animationjikan.domain.repository.CharacterRepository
import com.artem.animationjikan.domain.repository.MangaRepository
import com.artem.animationjikan.util.enums.FilterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val animationRepository: AnimationRepository,
    private val characterRepository: CharacterRepository,
    private val mangaRepository: MangaRepository,
) {

    suspend fun search(type: FilterType, query: String?): Flow<Result<List<HomeCommonEntity>>> {
        return when (type) {
            FilterType.ANIMATION -> {
                animationRepository.searchAnime(query = query).map { result ->
                    result.map { list ->
                        list.map {
                            it.toHomeCommonEntity(type = FilterType.ANIMATION)
                        }
                    }
                }
            }

            FilterType.MANGA -> {
                animationRepository.searchAnime(query = query).map { result ->
                    result.map { list ->
                        list.map {
                            it.toHomeCommonEntity(type = FilterType.ANIMATION)
                        }
                    }
                }
            }

            FilterType.VOICE_ACTOR -> {
                animationRepository.searchAnime(query = query).map { result ->
                    result.map { list ->
                        list.map {
                            it.toHomeCommonEntity(type = FilterType.ANIMATION)
                        }
                    }
                }
            }

            FilterType.CHARACTER -> {
                animationRepository.searchAnime(query = query).map { result ->
                    result.map { list ->
                        list.map {
                            it.toHomeCommonEntity(type = FilterType.ANIMATION)
                        }
                    }
                }
            }
        }
    }
}