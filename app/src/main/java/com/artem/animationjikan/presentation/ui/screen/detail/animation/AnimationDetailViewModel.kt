package com.artem.animationjikan.presentation.ui.screen.detail.animation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        val TAG: String? = AnimationDetailViewModel::class.simpleName
    }

    val animeId: Int? = savedStateHandle.get<String>("malId")?.toIntOrNull() ?: run {
        Log.e(TAG, "Anime ID not found in SavedStateHandle")
        null
    }

    init {
        if (animeId != null) {
            Log.d(TAG, "Received Anime ID: $animeId")
            // Use animeId to fetch details
        } else {
            // Handle the case where animeId is null (e.g., show an error state)
        }
    }
}
