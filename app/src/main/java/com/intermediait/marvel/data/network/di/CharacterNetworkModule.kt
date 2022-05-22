package com.intermediait.marvel.data.network.di

import com.intermediait.marvel.data.mappers.CharacterNetworkMapper
import com.intermediait.marvel.data.network.CharacterDataSourceImpl
import com.intermediait.marvel.data.network.api.MarvelAPI
import com.intermediait.marvel.data.repositories.CharacterDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object CharacterNetworkModule {

    @Provides
    fun provideCharacterNetworkMapper(): CharacterNetworkMapper = CharacterNetworkMapper()

    @Provides
    fun provideCharacterDataSource(
        marvelAPI: MarvelAPI,
        characterNetworkMapper: CharacterNetworkMapper
    ): CharacterDataSource =
        CharacterDataSourceImpl(marvelAPI, characterNetworkMapper)

}