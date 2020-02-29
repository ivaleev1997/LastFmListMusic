package com.education.core_api.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageItem(
    @SerializedName("#text")
    val url: String,
    val size: String
): Serializable