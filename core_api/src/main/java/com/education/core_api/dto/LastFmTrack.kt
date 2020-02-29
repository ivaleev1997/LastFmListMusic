package com.education.core_api.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LastFmTrack(
    val duration: String,
    val mbid: String,
    val name: String,
    @SerializedName("image")
    val images: List<ImageItem>,
    @SerializedName("playcount")
    val playCount: String,
    val artist: LastFmArtist,
    val url: String,
    val listeners: String
): Serializable