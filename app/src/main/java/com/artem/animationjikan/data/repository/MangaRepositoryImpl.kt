package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : MangaRepository {
    override suspend fun fetchTopManga() {
        client.getTopManga()
    }
}