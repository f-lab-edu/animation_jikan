package com.artem.animationjikan.presentation.ui.tab.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.usecase.LikeUsecase
import com.artem.animationjikan.domain.usecase.RemoveLikeUsecase
import com.artem.animationjikan.util.enums.FilterCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LikeViewModel @Inject constructor(
    likeUsecase: LikeUsecase,
    private val removeLikeUsecase: RemoveLikeUsecase
) : ViewModel() {
    private val _mediaTypeFilter = MutableStateFlow(FilterCategory.ALL)
    val currentMediaType: StateFlow<FilterCategory> = _mediaTypeFilter.asStateFlow()

    private val _likeList = MutableStateFlow<List<LikeEntity>>(emptyList())
    val likeList: StateFlow<List<LikeEntity>> = _likeList

    private val likeFlow: Flow<List<LikeEntity>> =
        currentMediaType.flatMapLatest { mediaType ->
            likeUsecase.execute(mediaType = mediaType.name)
        }.map { result ->
            result.getOrElse { error -> emptyList() }
        }

    init {
        viewModelScope.launch {
            likeFlow.collect {
                _likeList.value = it
            }
        }
    }

    fun setMediaTypeFilter(newType: FilterCategory) {
        if (newType != _mediaTypeFilter.value) {
            _likeList.value = emptyList()
            _mediaTypeFilter.value = newType
        }
    }

    fun removeLike(entity: LikeEntity) {
        viewModelScope.launch {
            removeLikeUsecase.execute(mediaId = entity.mediaId)
        }
    }
}