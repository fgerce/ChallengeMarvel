package com.intermediait.marvel.data.repositories.di

import com.intermediait.marvel.data.repositories.mappers.CharacterViewMapper
import com.intermediait.marvel.data.repositories.mappers.ComicMapper
import com.intermediait.marvel.data.repositories.mappers.EventViewMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewMappersModule {
    @Provides
    fun provideComicViewMapper(): ComicMapper = ComicMapper()

    @Provides
    fun provideEventViewMapper(comicMapper: ComicMapper): EventViewMapper =
        EventViewMapper(comicMapper)

    @Provides
    fun provideCharacterViewMapper(comicMapper: ComicMapper): CharacterViewMapper =
        CharacterViewMapper(comicMapper)
}