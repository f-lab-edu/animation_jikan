package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient

class MangaRepositoryImpl(
    private val client : JikanApiClient
) : MangaRepository{
    override suspend fun fetchTopManga() {
        client.getTopManga()
    }

}