package com.artem.animationjikan.data.repository

interface CharacterRepository {
    suspend fun fetchTopCharacters()
}