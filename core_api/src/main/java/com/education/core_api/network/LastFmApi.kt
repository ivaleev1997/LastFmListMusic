package com.education.core_api.network

import com.education.core_api.dto.LastFmApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface LastFmApi {
    @GET("?method=chart.gettoptracks")
    fun getTopTracksAsync() : Deferred<Response<LastFmApiResponse>>
}