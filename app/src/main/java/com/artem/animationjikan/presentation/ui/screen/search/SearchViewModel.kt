package com.artem.animationjikan.presentation.ui.screen.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.HomeCommonEntity
import com.artem.animationjikan.domain.usecase.SearchUseCase
import com.artem.animationjikan.util.enums.FilterType
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    companion object {
        val TAG: String? = SearchViewModel::class.simpleName
    }

    val contentList = MutableStateFlow<List<HomeCommonEntity>>(emptyList())

    var status by mutableStateOf(ViewModelState.Idle)

    init {
        execute()
    }

    fun execute(type: FilterType = FilterType.ANIMATION, query: String? = null) {
        status = ViewModelState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.search(type = type, query = query).collect { result ->
                result.onSuccess {
                    status = ViewModelState.Success
                    contentList.value = it
                }.onFailure { error ->
                    Log.e(TAG, error.message.toString())
                    status = ViewModelState.Error
                }
            }
        }
    }

}