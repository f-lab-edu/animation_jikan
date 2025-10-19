package com.artem.animationjikan.data.repository

interface MangaRepository {
    suspend fun fetchTopManga()
}