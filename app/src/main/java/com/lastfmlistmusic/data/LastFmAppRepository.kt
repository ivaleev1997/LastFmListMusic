package com.lastfmlistmusic.data

import com.lastfmlistmusic.data.remote.api.LastFmApi
import com.lastfmlistmusic.data.remote.model.LastFmTrack
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LastFmAppRepository
@Inject constructor(
        private val api: LastFmApi
) {

    suspend fun loadMusic(): List<LastFmTrack>?{
        var result: List<LastFmTrack>? = null
        var call = api.getTopTracksAsync()
        var response = call.await()
        if (response.isSuccessful) {
            println(response.body()!!.tracks.listLastFmTopTracks)
            result = response.body()!!.tracks.listLastFmTopTracks
        }

        return result
    }
}