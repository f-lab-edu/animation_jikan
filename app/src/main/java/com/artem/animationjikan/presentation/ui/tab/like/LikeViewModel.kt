package com.artem.animationjikan.presentation.ui.tab.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.usecase.AddLikeUsecase
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

@HiltViewModel
class LikeViewModel @Inject constructor(
    likeUsecase: LikeUsecase,
    private val addLikeUsecase: AddLikeUsecase,
    private val removeLikeUsecase: RemoveLikeUsecase
) : ViewModel() {
    private val _preservedList = MutableStateFlow<List<LikeEntity>>(emptyList())
    val preservedList: StateFlow<List<LikeEntity>> = _preservedList

    private val _mediaTypeFilter = MutableStateFlow(FilterCategory.ALL)
    val currentMediaType: StateFlow<FilterCategory> = _mediaTypeFilter.asStateFlow()

    fun setMediaTypeFilter(newType: FilterCategory) {
        if (newType != _mediaTypeFilter.value) {
            _preservedList.value = emptyList()
            _mediaTypeFilter.value = newType
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val likeDbFlow: Flow<List<LikeEntity>> =
        currentMediaType.flatMapLatest { mediaType ->
            likeUsecase.execute(mediaType = mediaType.name)
        }.map { result ->
            result.getOrElse { error -> emptyList() }
        }

    init {
        viewModelScope.launch {
            likeDbFlow.collect { list ->
                val currentList = _preservedList.value

                val removedList = currentList.filter { it !in list }

                val addedItems = list.filter { it !in currentList }

                val updateList = currentList.map { item ->
                    if (item in removedList) {
                        item.copy(isLiked = false)
                    } else {
                        item
                    }
                } + addedItems.map { it.copy(isLiked = true) }

                val finalUniqueList = updateList.groupBy { it.mediaId }
                    .mapValues { (_, list) ->
                        list.find { it.isLiked } ?: list.firstOrNull()
                    }.values
                    .filterNotNull()
                    .toList()

                _preservedList.value = finalUniqueList
            }
        }
    }

    fun toggle(entity: LikeEntity) {
        viewModelScope.launch {
            if (entity.isLiked) {
                removeLikeUsecase.execute(mediaId = entity.mediaId)
            } else {
                addLikeUsecase.execute(entity)
            }
        }
    }
}