package com.intermediait.marvel.data.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.intermediait.marvel.data.network.api.HttpConfig
import com.intermediait.marvel.data.network.api.MarvelAPI
import com.intermediait.marvel.view.config.HttpConfigImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(
        httpConfig: HttpConfig,
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(httpConfig.baseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Reusable
    fun provideOkHttpClient(httpConfig: HttpConfig): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(httpConfig.apiTimeout(), TimeUnit.MILLISECONDS)
            .readTimeout(httpConfig.apiTimeout(), TimeUnit.MILLISECONDS)
            .writeTimeout(httpConfig.apiTimeout(), TimeUnit.MILLISECONDS)
            .build()


    @Provides
    fun provideHttpConfig(): HttpConfig = HttpConfigImpl()

    @Provides
    internal fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelAPI = retrofit.create(MarvelAPI::class.java)

}