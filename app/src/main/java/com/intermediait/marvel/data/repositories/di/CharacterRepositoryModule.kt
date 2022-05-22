package com.intermediait.marvel.data.repositories.di

import com.intermediait.marvel.data.repositories.CharacterDataSource
import com.intermediait.marvel.data.repositories.CharacterRepositoryImpl
import com.intermediait.marvel.data.repositories.mappers.CharacterViewMapper
import com.intermediait.marvel.domain.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object CharacterRepositoryModule {

    @Provides
    fun provideCharacterRepository(
        characterDataSource: CharacterDataSource,
        characterViewMapper: CharacterViewMapper
    ): CharacterRepository = CharacterRepositoryImpl(characterDataSource, characterViewMapper)

}