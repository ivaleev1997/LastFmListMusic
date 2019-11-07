package com.lastfmlistmusic.data.remote.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
) : Serializable {
    object ItemImageBindingAdapter {
        @JvmStatic
        @BindingAdapter("app:glideImageLoad")
        fun loadImage(imageView: ImageView, trackList: List<ImageItem>) {
            val url = trackList.first{ it.size == "medium" }.url
            Glide.with(imageView).load(url).into(imageView)
        }
    }
}

data class ImageItem(
    @SerializedName("#text")
    val url: String,
    val size: String
) : Serializable

data class LastFmArtist(
    val mbid: String,
    val name: String,
    @SerializedName("image")
    val images: List<ImageItem>,
    val streamable: String,
    @SerializedName("playcount")
    val playCount: String,
    val url: String
) : Serializable

data class LastFmTopTracks(
    @SerializedName("track")
    val listLastFmTopTracks: List<LastFmTrack>
)