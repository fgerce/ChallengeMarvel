package com.intermediait.marvel.domain


sealed class MyResult<out T> {
    data class Success<T>(val data: T) : MyResult<T>()
    data class Failure(val exception: Exception) : MyResult<Nothing>()
}