package com.intermediait.marvel.data.network.models

data class ComicNetwork(
    val name: String?,
)

data class ComicResponse(
    val items: List<ComicNetwork>,
)