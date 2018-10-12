package com.hackathlon.exoplayertask.api.response

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable


open class DataModel : RealmObject(), Serializable {

    @PrimaryKey
    @SerializedName("id")
    var id: String? = null

    @SerializedName("description")
    var descp: String? = null


    @SerializedName("url")
    var videourl: String? = null
    @SerializedName("thumb")
    var image: String? = null
    @SerializedName("title")
    var title: String? = null


    var playedDuration: Long = 0
//    var played_percentage : Int=0

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        return id?.equals((other as? DataModel)?.id) ?: false
    }


}

