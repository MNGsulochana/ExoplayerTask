package com.hackathlon.exoplayertask.ui.home

import com.google.gson.annotations.SerializedName

data class Video(
        @SerializedName("description") val description: String,
        @SerializedName("id") val id: String,
        @SerializedName("thumb") val thumb: String,
        @SerializedName("title") val title: String,
        @SerializedName("url") val url: String
)