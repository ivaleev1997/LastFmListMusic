package com.lastfmlistmusic.data.remote.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lastfmlistmusic.BuildConfig
import com.lastfmlistmusic.LASTFM_API_KEY
import com.lastfmlistmusic.LASTFM_BASE_URL
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
        val okHttpBld = OkHttpClient.Builder().apply {
            addInterceptor{chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()
                val url = originalHttpUrl
                    .newBuilder()
                    .addQueryParameter("api_key", LASTFM_API_KEY)
                    .addQueryParameter("format", "json")
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            if (BuildConfig.DEBUG)
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
        }

        val okHttpClient = okHttpBld.build()
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(LASTFM_BASE_URL)
            .build()
    }
}