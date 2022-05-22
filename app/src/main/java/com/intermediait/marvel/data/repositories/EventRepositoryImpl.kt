package com.intermediait.marvel.data.repositories

import com.intermediait.marvel.data.repositories.mappers.EventViewMapper
import com.intermediait.marvel.domain.EventRepository
import com.intermediait.marvel.domain.MyResult
import com.intermediait.marvel.domain.models.Event

class EventRepositoryImpl(
    private val eventDataSource: EventDataSource,
    private val eventViewMapper: EventViewMapper,
) : EventRepository {
    override suspend fun getEvents(): MyResult<List<Event>> {
        return when(val data = eventDataSource.getEvents()){
            is MyResult.Failure -> {
                MyResult.Failure(data.exception)
            }
            is MyResult.Success -> {
                MyResult.Success(data.data.map { eventViewMapper(it) })
            }
        }
    }
}