package com.artem.animationjikan.util.event

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}