package com.lastfmlistmusic

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lastfmlistmusic.api.LastFmApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class LastFmApiSingleton {
    @Volatile private var instance: LastFmApi? = null

    fun getInstance(): LastFmApi {
        if (instance == null) {
            return synchronized(this) {
                if (instance != null)
                    instance as LastFmApi
                else {
                    instance = createInstance()
                    instance as LastFmApi
                }

            }
        }
        return instance as LastFmApi
    }

    private fun createInstance(): LastFmApi {
        return retrofit().create(LastFmApi::class.java)
    }

    private fun retrofit(): Retrofit {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpInterceptor).build()

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).client(okHttpClient).addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(LASTFM_BASE_URL).build()
    }
}