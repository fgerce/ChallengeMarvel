package com.intermediait.marvel.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.intermediait.marvel.data.network.CharacterPagingSource
import com.intermediait.marvel.data.repositories.mappers.CharacterViewMapper
import com.intermediait.marvel.domain.CharacterRepository
import com.intermediait.marvel.domain.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 15

class CharacterRepositoryImpl(
    private val characterDataSource: CharacterDataSource,
    private val characterViewMapper: CharacterViewMapper
) : CharacterRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { CharacterPagingSource(characterDataSource) }
        ).flow
            .map { pagingData ->
                pagingData.map { character ->
                    characterViewMapper(character)
                }
            }
    }

}