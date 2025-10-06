package com.artem.animationjikan.util.enums

import androidx.annotation.StringRes
import com.artem.animationjikan.R

enum class FilterCategory(@StringRes val stringRes: Int) {
    ALL(R.string.All),
    ANIMATION(R.string.animation),
    MANGA(R.string.manga),
    VOICE_ACTOR(R.string.actor),
    CHARACTER(R.string.character)
}