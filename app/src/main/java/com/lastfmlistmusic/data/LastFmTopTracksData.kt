package com.lastfmlistmusic.data

import com.google.gson.annotations.SerializedName

data class LastFmTrack(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val images: List<ImageItem>,
    @SerializedName("playcount")
    val playCount: String,
    @SerializedName("artist")
    val artist: LastFmArtist,
    @SerializedName("url")
    val url: String
)

data class ImageItem(
    @SerializedName("#text")
    val url: String,
    @SerializedName("size")
    val size: String
)

data class LastFmArtist(
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val images: List<ImageItem>,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("playcount")
    val playCount: String,
    @SerializedName("url")
    val url: String
)

data class LastFmTopTracks(
    @SerializedName("track")
    val listLastFmTopTracks: List<LastFmTrack>
)