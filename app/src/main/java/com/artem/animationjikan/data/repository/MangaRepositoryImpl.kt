package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : MangaRepository {
    override suspend fun fetchTopManga(): List<CommonHomeContentModel> {
        return client.getTopManga().data?.map { mangaDTO ->
            CommonHomeContentModel(
                id = mangaDTO.malId,
                type = FilterCategory.MANGA,
                imageUrl = mangaDTO.images.jpg.imageUrl,
            )
        } ?: emptyList()
    }
}