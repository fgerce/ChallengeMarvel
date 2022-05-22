package com.intermediait.marvel.data.network.di

import com.intermediait.marvel.data.mappers.EventNetworkMapper
import com.intermediait.marvel.data.network.EventDataSourceImpl
import com.intermediait.marvel.data.network.api.MarvelAPI
import com.intermediait.marvel.data.repositories.EventDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EventNetworkModule {
    @Provides
    fun provideEventNetworkMapper(): EventNetworkMapper = EventNetworkMapper()

    @Provides
    fun provideEventDataSource(
        marvelAPI: MarvelAPI,
        eventNetworkMapper: EventNetworkMapper
    ): EventDataSource =
        EventDataSourceImpl(marvelAPI, eventNetworkMapper)

}