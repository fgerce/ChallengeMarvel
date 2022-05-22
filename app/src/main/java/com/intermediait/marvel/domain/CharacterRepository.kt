package com.intermediait.marvel.domain

import androidx.paging.PagingData
import com.intermediait.marvel.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}