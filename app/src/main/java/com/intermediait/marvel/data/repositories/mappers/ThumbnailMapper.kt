package com.intermediait.marvel.data.repositories.mappers

import com.intermediait.marvel.data.network.models.MarvelThumbnail
import com.intermediait.marvel.domain.models.ThumbnailURL

class ThumbnailMapper {
    operator fun invoke(thumbnail: MarvelThumbnail?): ThumbnailURL {
        return if (thumbnail != null) {
            if (!thumbnail.path.isNullOrEmpty() && !thumbnail.extension.isNullOrEmpty()) {
                ThumbnailURL.URL(thumbnail.path + "/standard_xlarge." + thumbnail.extension)
            } else {
                ThumbnailURL.Empty
            }
        }else{
            ThumbnailURL.Empty
        }
    }
}