package com.lastfmlistmusic

import com.lastfmlistmusic.api.LastFmApi
import com.lastfmlistmusic.data.LastFmTrack

class LastFmAppRepository(private val api: LastFmApi) {

    suspend fun loadMusic(): List<LastFmTrack>?{
        var result: List<LastFmTrack>? = null
        var call = api.getTopTracks()
        var response = call.await()
        if (response.isSuccessful) {
            println(response.body()!!.tracks.listLastFmTopTracks)
            result = response.body()!!.tracks.listLastFmTopTracks
        }

        return result
    }
}