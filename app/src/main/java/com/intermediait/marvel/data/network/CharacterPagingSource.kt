package com.intermediait.marvel.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.intermediait.marvel.data.network.models.CharacterNetwork
import com.intermediait.marvel.data.repositories.CharacterDataSource
import com.intermediait.marvel.domain.MyResult
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

private const val STARTING_PAGE_INDEX = 0

class CharacterPagingSource(
    private val characterDataSource: CharacterDataSource
) : PagingSource<Int, CharacterNetwork>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterNetwork>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterNetwork> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            when (val response = characterDataSource.getCharacters(page = page, size = params.loadSize)) {
                is MyResult.Failure -> {
                    LoadResult.Error(Exception(response.exception.toString()))
                }
                is MyResult.Success -> {
                    LoadResult.Page(
                        data = response.data,
                        prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (response.data.isEmpty()) null else page + 1
                    )
                }
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}