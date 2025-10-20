package com.artem.animationjikan.data.repository

import com.artem.animationjikan.presentation.model.CommonHomeContentModel

interface MangaRepository {
    suspend fun fetchTopManga() : List<CommonHomeContentModel>
}