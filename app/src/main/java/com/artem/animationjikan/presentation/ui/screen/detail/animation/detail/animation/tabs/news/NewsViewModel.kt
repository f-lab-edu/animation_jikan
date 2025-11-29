package com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.animation.tabs.news

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.artem.animationjikan.domain.entities.NewsEntity
import com.artem.animationjikan.domain.usecase.NewsUsecase
import com.artem.animationjikan.presentation.ui.screen.detail.animation.detail.TabBaseViewModel
import com.artem.animationjikan.util.NO_ERROR_MESSAGE
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUsecase,
) : TabBaseViewModel<NewsEntity>() {

    override var type: DetailTabs = DetailTabs.FIRST

    override fun execute(malId: Int) {
        _state.value = ViewModelState.Loading
        _list.value = emptyList()
        viewModelScope.launch(Dispatchers.IO) {
            val result = newsUseCase.execute(malId)
            result.onSuccess { list ->
                this@NewsViewModel._list.value = list
                _state.value = ViewModelState.Success
            }.onFailure { error ->
                Log.e(TAG, error.message ?: NO_ERROR_MESSAGE)
                _state.value = ViewModelState.Error
            }
        }
    }


    companion object {
        val TAG: String? = NewsViewModel::class.simpleName
    }

}