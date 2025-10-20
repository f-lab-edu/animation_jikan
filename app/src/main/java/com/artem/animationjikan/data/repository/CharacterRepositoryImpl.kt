package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : CharacterRepository {
    override suspend fun fetchTopCharacters() {
        client.getTopCharacters()
    }
}