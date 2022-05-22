package com.intermediait.marvel.data.repositories

import com.intermediait.marvel.data.network.models.EventNetwork
import com.intermediait.marvel.domain.MyResult

interface EventDataSource {
    suspend fun getEvents() : MyResult<List<EventNetwork>>
}