package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.artem.animationjikan.util.enums.DetailTabs
import com.artem.animationjikan.util.enums.ViewModelState

abstract class TabBaseViewModel<T>() : ViewModel() {
    protected val _list: MutableState<List<T>> = mutableStateOf(emptyList())

    val list: State<List<T>> = _list

    abstract val type: DetailTabs

    protected var _state: MutableState<ViewModelState> = mutableStateOf(ViewModelState.Idle)

    val state: State<ViewModelState> = _state

    abstract fun execute(malId: Int)
}