package com.artem.animationjikan.presentation.ui.tab.like

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.R
import com.artem.animationjikan.domain.entities.LikeEntity
import com.artem.animationjikan.domain.usecase.LikeUseCase
import com.artem.animationjikan.domain.usecase.RemoveLikeUseCase
import com.artem.animationjikan.util.enums.FilterCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LikeViewModel @Inject constructor(
    likeUseCase: LikeUseCase,
    private val removeLikeUseCase: RemoveLikeUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "LikeViewModel"
    }

    private val _mediaTypeFilter = MutableStateFlow(FilterCategory.ALL)
    val currentMediaType: StateFlow<FilterCategory> = _mediaTypeFilter.asStateFlow()

    private val _likeList = MutableStateFlow<List<LikeEntity>>(emptyList())
    val likeList: StateFlow<List<LikeEntity>> = _likeList

    private val likeFlow: Flow<List<LikeEntity>> =
        currentMediaType.flatMapLatest { mediaType ->
            var type: String? = mediaType.name

            if (mediaType.stringRes == R.string.All) {
                type = null
            }

            likeUseCase.execute(mediaType = type)
        }.catch {
            Log.e(TAG, "error: $it")
            emit(emptyList())
        }

    init {
        likeFlow.onEach {
            _likeList.value = it
        }.launchIn(viewModelScope)
    }

    fun setMediaTypeFilter(newType: FilterCategory) {
        if (newType != _mediaTypeFilter.value) {
            _likeList.value = emptyList()
            _mediaTypeFilter.value = newType
        }
    }

    fun removeLike(entity: LikeEntity) {
        viewModelScope.launch {
            removeLikeUseCase.execute(mediaId = entity.mediaId)
        }
    }
}