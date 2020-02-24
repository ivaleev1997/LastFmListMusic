package com.lastfmlistmusic.di

import com.lastfmlistmusic.data.remote.api.LastFmApi
import com.lastfmlistmusic.data.remote.api.LastFmApiSingleton
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLastFmApi(): LastFmApi {
        return LastFmApiSingleton().getInstance()
    }
}