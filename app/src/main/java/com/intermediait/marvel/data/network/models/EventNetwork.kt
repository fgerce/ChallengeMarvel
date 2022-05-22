package com.intermediait.marvel.data.network.models

data class EventNetwork(
    val id: Int,
    val title: String?,
    val thumbnail: MarvelThumbnail?,
    val start: String?,
    val comics: ComicResponse?,
)

data class EventResponse(
    val results: List<EventNetwork>,
)

data class EventDataWrapper(
    val data: EventResponse,
)
