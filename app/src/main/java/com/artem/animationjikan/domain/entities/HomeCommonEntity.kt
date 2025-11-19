package com.artem.animationjikan.domain.entities

import android.os.Parcelable
import com.artem.animationjikan.util.enums.FilterCategory
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeCommonEntity(
    val id: Int = -1,
    val type: FilterCategory,
    val likeStatus: Boolean = false,
    val imageUrl: String? = null,
) : Parcelable