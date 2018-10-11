package com.hackathlon.exoplayertask.ui.player.model

import io.realm.RealmObject
import java.io.Serializable

open class Playercontinuity : RealmObject(), Serializable {

    var position: Long? = null
    var duration: Long? = null
    var descp: String? = null
    var id: String? = null

    var videourl_cont: String? = null

    var image: String? = null

    var title_contn: String? = null
}
