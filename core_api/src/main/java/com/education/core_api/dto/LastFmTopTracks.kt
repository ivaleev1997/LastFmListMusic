package com.education.core_api.dto

import com.google.gson.annotations.SerializedName

data class LastFmTopTracks(
    @SerializedName("track")
    val listLastFmTopTracks: List<LastFmTrack>
)