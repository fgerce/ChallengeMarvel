package com.intermediait.marvel.data.mappers

import com.intermediait.marvel.data.network.models.EventDataWrapper
import com.intermediait.marvel.data.network.models.EventNetwork
import com.intermediait.marvel.domain.MyResult

class EventNetworkMapper {
    operator fun invoke(result: MyResult<EventDataWrapper>): MyResult<List<EventNetwork>> {
        return when (result) {
            is MyResult.Failure -> {
                MyResult.Failure(result.exception)
            }
            is MyResult.Success -> {
                MyResult.Success(mapRoute(result.data))
            }
        }
    }

    private fun mapRoute(eventDataWrapper: EventDataWrapper): List<EventNetwork> {
        with(eventDataWrapper) {
            return data.results
        }
    }
}