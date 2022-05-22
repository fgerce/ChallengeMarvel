package com.intermediait.marvel.data.network.api

interface HttpConfig {
    fun baseUrl(): String
    fun apiTimeout(): Long
}