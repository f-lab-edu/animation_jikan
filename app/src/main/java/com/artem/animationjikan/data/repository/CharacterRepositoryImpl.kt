package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient

class CharacterRepositoryImpl(
    private val client : JikanApiClient
) : CharacterRepository{
    override suspend fun fetchTopCharacters() {
        client.getTopCharacters()
    }
}