package com.artem.animationjikan.presentation.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.usecase.SearchUseCase
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Suppress("TYPE_INTERSECTION_AS_REIFIED_WARNING")
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    companion object {
        val TAG: String? = SearchViewModel::class.simpleName
    }

    val contentList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    var status = MutableStateFlow(ViewModelState.Idle)

    private val searchFilter = MutableStateFlow(FilterType.ANIMATION)

    private var query = MutableStateFlow("")

    fun onQueryChange(query: String) {
        this.query.value = query
    }

    fun updateFilter(type: FilterType) {
        searchFilter.value = type
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val searchResult: StateFlow<Result<List<HomeCommonEntity>>> =
        combine(query, searchFilter) {
            Pair(query, searchFilter)
        }.debounce(300L)
            .onEach {
                status.value = ViewModelState.Loading
            }
            .flatMapLatest { (query, filter) ->
                searchUseCase.search(type = filter.value, query = query.value)
            }.onEach { result ->
                result.onSuccess {
                    status.value = ViewModelState.Success
                    contentList.value = it
                }.onFailure {
                    status.value = ViewModelState.Error
                    Log.e(TAG, it.message.toString())
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.success(emptyList())
            )

}