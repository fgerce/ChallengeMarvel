package com.intermediait.marvel.view.config

import com.intermediait.marvel.BuildConfig
import com.intermediait.marvel.data.network.api.HttpConfig

class HttpConfigImpl : HttpConfig {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun apiTimeout(): Long {
        return 30000
    }
}