package com.lastfmlistmusic.data.remote.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface LastFmApi {
    @GET("?method=chart.gettoptracks")
    fun getTopTracksAsync() : Deferred<Response<LastFmApiResponse>>
}