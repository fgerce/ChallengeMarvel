package com.intermediait.marvel.domain.models

import android.view.View

class Event (
    val id: Int,
    val title: String,
    val thumbnail: ThumbnailURL,
    val start: String,
    val comics: List<Comic>,
    var state: Int = View.GONE
        ) {
}