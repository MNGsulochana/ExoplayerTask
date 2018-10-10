package com.hackathlon.exoplayertask.api.response

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable


open class DataModel :RealmObject() ,Serializable {
    @SerializedName("description")
    var descp: String? = null
    @PrimaryKey
    @SerializedName("id")
    var id: String? = null
    @SerializedName("url")
    var videourl: String? = null
    @SerializedName("thumb")
    var image: String? = null
    @SerializedName("title")
    var  title: String?=null


}
