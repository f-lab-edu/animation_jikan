package com.artem.animationjikan.domain.entities

import android.os.Parcelable
import com.artem.animationjikan.util.enums.FilterType
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeCommonEntity(
    val id: Int = -1,
    val type: FilterType,
    val likeStatus: Boolean = false,
    val imageUrl: String? = null,
) : Parcelable