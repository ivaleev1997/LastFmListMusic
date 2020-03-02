package com.education.core_api.mediator

import com.education.core_api.network.LastFmApi

interface NetworkProvider {
    //fun provideNetwork(): LastFmApiContract

    fun provideLastFmApi(): LastFmApi
}