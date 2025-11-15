package com.artem.animationjikan.util.event

sealed class UiEvent {
    data class ShowToast(val message: Int) : UiEvent()
    data class ShowSnackBar(val message: Int) : UiEvent()
    data class ShowDialog(val message: Int) : UiEvent()
}