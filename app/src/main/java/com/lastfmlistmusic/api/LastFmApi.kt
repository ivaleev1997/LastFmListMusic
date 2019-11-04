package com.lastfmlistmusic.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface LastFmApi {
    @GET("?method=chart.gettoptracks&api_key=fb71e81988d9dee6ba81f6a022a735cc&format=json")
    fun getTopTracks() : Deferred<Response<LastFmApiResponse>>
}