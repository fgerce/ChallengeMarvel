package com.intermediait.marvel.data.network.api

import com.intermediait.marvel.data.network.models.CharacterDataWrapper
import com.intermediait.marvel.data.network.models.EventDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {
    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") timestamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int? = 0,
    ) : Response<CharacterDataWrapper>

    @GET("events")
    suspend fun getEvents(
        @Query("orderBy") orderBy: String = "-startDate",
        @Query("limit") limit: Int = 25,
        @Query("ts") timestamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String,
    ) : Response<EventDataWrapper>
}