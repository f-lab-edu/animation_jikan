package com.artem.animationjikan.data.repository

import com.artem.animationjikan.data.service.remote.JikanApiClient
import com.artem.animationjikan.presentation.model.CommonHomeContentModel
import com.artem.animationjikan.util.enums.FilterCategory
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val client: JikanApiClient
) : CharacterRepository {
    override suspend fun fetchTopCharacters(): List<CommonHomeContentModel>{
        return client.getTopCharacters().data.map { characterDTO ->
            CommonHomeContentModel(
                id = characterDTO.malId,
                type = FilterCategory.CHARACTER,
                imageUrl = characterDTO.images.jpg.imageUrl
            )
        }
    }
}