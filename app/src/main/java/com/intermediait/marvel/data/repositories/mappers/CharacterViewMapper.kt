package com.intermediait.marvel.data.repositories.mappers

import com.intermediait.marvel.data.network.models.CharacterNetwork
import com.intermediait.marvel.data.network.models.MarvelThumbnail
import com.intermediait.marvel.domain.models.Character
import com.intermediait.marvel.domain.models.ThumbnailURL

class CharacterViewMapper(
    private val comicMapper: ComicMapper
) {
    operator fun invoke(characterNetwork: CharacterNetwork): Character {
        with (characterNetwork) {
            return Character(
                id = id,
                name = name.toString(),
                thumbnail = ThumbnailMapper()(thumbnail),
                description = description.toString(),
                comics = comicMapper(comics)
            )
        }
    }
}