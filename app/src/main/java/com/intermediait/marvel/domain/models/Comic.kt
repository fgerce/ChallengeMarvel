package com.intermediait.marvel.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val name: String,
) : Parcelable{
}