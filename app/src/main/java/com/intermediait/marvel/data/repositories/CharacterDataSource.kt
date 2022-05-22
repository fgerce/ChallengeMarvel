package com.intermediait.marvel.data.repositories

import com.intermediait.marvel.data.network.models.CharacterNetwork
import com.intermediait.marvel.domain.MyResult

interface CharacterDataSource {
    suspend fun getCharacters(page: Int, size: Int) : MyResult<List<CharacterNetwork>>
}