package com.intermediait.marvel.data.repositories.di

import com.intermediait.marvel.data.repositories.EventDataSource
import com.intermediait.marvel.data.repositories.EventRepositoryImpl
import com.intermediait.marvel.data.repositories.mappers.EventViewMapper
import com.intermediait.marvel.domain.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object EventRepositoryModule {

    @Provides
    fun provideEventRepository(
        eventDataSource: EventDataSource,
        eventViewMapper: EventViewMapper
    ): EventRepository = EventRepositoryImpl(eventDataSource, eventViewMapper)

}