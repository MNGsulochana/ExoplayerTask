package com.hackathlon.exoplayertask.api.response

import com.google.gson.annotations.SerializedName


open class DataModel {
    @SerializedName("description")
    var descp: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("url")
    var videourl: String? = null
    @SerializedName("thumb")
    var image: String? = null
}
