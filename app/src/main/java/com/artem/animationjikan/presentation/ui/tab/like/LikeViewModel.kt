package com.artem.animationjikan.presentation.ui.tab.like

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.usecase.LikeUsecase
import com.artem.animationjikan.util.enums.FilterCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    likeUsecase: LikeUsecase
) : ViewModel() {

    init {
        Log.e("LikeViewModel" , "init8")
    }

    val likeAnimeList: StateFlow<List<LikeEntity>> =
        likeUsecase.execute(mediaType = FilterCategory.ANIMATION.name)
            .map { result ->
                result.getOrElse { error ->
                    emptyList()
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}