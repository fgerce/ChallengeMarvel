package com.intermediait.marvel.data.network

import com.intermediait.marvel.domain.MyResult
import retrofit2.Response

fun <T : Any> Response<T>.toResult(): MyResult<T> {
    return if(isSuccessful && body() != null){
        MyResult.Success(body()!!)
    }else{
        when(code()){
            500 -> {
                MyResult.Failure(Exception("No internet"))
            }
            else -> {
                MyResult.Failure(Exception("undefined"))
            }
        }
    }
}