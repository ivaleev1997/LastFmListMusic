package com.education.songslist.repository

import com.education.core_api.dto.LastFmTrack
import com.education.core_api.network.LastFmApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LastFmAppRepositoryImpl
@Inject constructor(
    private val api: LastFmApi
) : LastFmAppRepository {

    override suspend fun loadMusic(): List<LastFmTrack>? {
        var result: List<LastFmTrack>? = null
        val call = api.getTopTracksAsync()
        val response = call.await()
        if (response.isSuccessful) {
            println(response.body()!!.tracks.listLastFmTopTracks)
            result = response.body()!!.tracks.listLastFmTopTracks
        }

        return result
    }
}