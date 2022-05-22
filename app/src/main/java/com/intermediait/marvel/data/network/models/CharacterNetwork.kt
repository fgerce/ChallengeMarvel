package com.intermediait.marvel.data.network.models

data class CharacterNetwork(
    val id: Int,
    val name: String?,
    val thumbnail: MarvelThumbnail?,
    val description: String?,
    val comics: ComicResponse?
) {
}

data class CharacterResponse(
    val results: List<CharacterNetwork>
) {

}

data class CharacterDataWrapper(
    val data: CharacterResponse
) {
}