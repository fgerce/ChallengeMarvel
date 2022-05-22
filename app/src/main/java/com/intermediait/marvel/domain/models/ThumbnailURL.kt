package com.intermediait.marvel.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ThumbnailURL : Parcelable {
    @Parcelize
    object Empty : ThumbnailURL(), Parcelable
    @Parcelize
    data class URL(val url: String) : ThumbnailURL(), Parcelable
}