package com.intermediait.marvel.domain

import com.intermediait.marvel.domain.models.Event

interface EventRepository {
    suspend fun getEvents(): MyResult<List<Event>>
}
