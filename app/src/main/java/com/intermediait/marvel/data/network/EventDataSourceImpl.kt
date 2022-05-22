package com.intermediait.marvel.data.network

import com.intermediait.marvel.BuildConfig
import com.intermediait.marvel.data.mappers.EventNetworkMapper
import com.intermediait.marvel.data.network.api.MarvelAPI
import com.intermediait.marvel.data.network.models.EventNetwork
import com.intermediait.marvel.data.repositories.EventDataSource
import com.intermediait.marvel.data.utils.MD5Hash
import com.intermediait.marvel.domain.MyResult

class EventDataSourceImpl(
    private val marvelAPI: MarvelAPI,
    private val eventMapper: EventNetworkMapper
) : EventDataSource {
    override suspend fun getEvents(): MyResult<List<EventNetwork>> {
        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
        val publicKey = BuildConfig.PUBLIC_KEY
        return try {
            val response = marvelAPI.getEvents(
                timestamp = ts,
                publicKey = publicKey,
                hash = MD5Hash.hashAlgorithm(ts, publicKey),
            ).toResult()
            return eventMapper(response)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }
}
