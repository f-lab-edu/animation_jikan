package com.artem.animationjikan.data.repository

import com.artem.animationjikan.presentation.model.CommonHomeContentModel

interface CharacterRepository {
    suspend fun fetchTopCharacters(): List<CommonHomeContentModel>
}