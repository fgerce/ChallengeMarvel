package com.intermediait.marvel.data.repositories.mappers

import com.intermediait.marvel.data.network.models.ComicNetwork
import com.intermediait.marvel.data.network.models.ComicResponse
import com.intermediait.marvel.domain.models.Comic

class ComicMapper {
    operator fun invoke(comicsResponse: ComicResponse?): List<Comic> {
        return if (comicsResponse?.items != null) {
            comicsResponse.items.map { comicMapper(it) }
        } else {
            emptyList()
        }
    }

    private fun comicMapper(comicNetwork: ComicNetwork): Comic {
        with(comicNetwork) {
            return Comic(
                name = name.toString(),
            )
        }
    }
}