package com.education.core_api.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LastFmArtist(
    val mbid: String,
    val name: String,
    @SerializedName("image")
    val images: List<ImageItem>,
    val streamable: String,
    @SerializedName("playcount")
    val playCount: String,
    val url: String
): Serializable