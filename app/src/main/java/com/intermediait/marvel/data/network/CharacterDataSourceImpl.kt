package com.intermediait.marvel.data.network

import com.intermediait.marvel.BuildConfig
import com.intermediait.marvel.data.mappers.CharacterNetworkMapper
import com.intermediait.marvel.data.network.api.MarvelAPI
import com.intermediait.marvel.data.network.models.CharacterNetwork
import com.intermediait.marvel.data.repositories.CharacterDataSource
import com.intermediait.marvel.data.utils.MD5Hash
import com.intermediait.marvel.domain.MyResult

class CharacterDataSourceImpl(
    private val marvelAPI: MarvelAPI,
    private val characterMapper: CharacterNetworkMapper
) : CharacterDataSource {

    override suspend fun getCharacters(page: Int, size: Int): MyResult<List<CharacterNetwork>> {
        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
        val publicKey = BuildConfig.PUBLIC_KEY
        return try {
            val response = marvelAPI.getCharacters(
                timestamp = ts,
                publicKey = publicKey,
                hash = MD5Hash.hashAlgorithm(ts, publicKey),
                offset = page * size,
            ).toResult()
            return characterMapper(response)
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }
}