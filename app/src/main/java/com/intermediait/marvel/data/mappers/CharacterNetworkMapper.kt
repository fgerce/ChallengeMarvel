package com.intermediait.marvel.data.mappers

import com.intermediait.marvel.data.network.models.CharacterDataWrapper
import com.intermediait.marvel.data.network.models.CharacterNetwork
import com.intermediait.marvel.domain.MyResult

class CharacterNetworkMapper {
    operator fun invoke(result: MyResult<CharacterDataWrapper>): MyResult<List<CharacterNetwork>> {
        return when (result) {
            is MyResult.Failure -> {
                MyResult.Failure(result.exception)
            }
            is MyResult.Success -> {
                MyResult.Success(mapRoute(result.data))
            }
        }
    }

    private fun mapRoute(characterDataWrapper: CharacterDataWrapper): List<CharacterNetwork> {
        with(characterDataWrapper) {
            return data.results
        }
    }

}