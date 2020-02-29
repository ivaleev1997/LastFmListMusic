package com.education.core_impl

import com.education.core_api.network.LastFmApi
import com.education.core_api.network.LastFmApiContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLastFmApi(lastFmApiContract: LastFmApiContract): LastFmApi {
        return lastFmApiContract.getInstance()
    }

    @Singleton
    @Provides
    fun provideLastFmContract(): LastFmApiContract {
        return LastFmApiImpl()
    }
}