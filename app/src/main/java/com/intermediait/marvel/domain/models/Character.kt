package com.intermediait.marvel.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val thumbnail: ThumbnailURL,
    val description: String,
    val comics: List<Comic>
) : Parcelable