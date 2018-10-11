package com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp

import android.content.Context
import com.hackathlon.exoplayertask.base.PresenterImpl
import com.hackathlon.exoplayertask.injection.annotate.ScopeContext
import javax.inject.Inject

class PlayListPresenter
@Inject constructor(@param:ScopeContext private val context: Context, private val repositor: PlayListContract.Repository) : PresenterImpl<PlayListContract.View>(), PlayListContract.Presenter {
    override fun attach(view: PlayListContract.View) {
        super.attach(view)
    }

    override fun message(message: Int) {

    }

    override fun message(message: String) {

    }
}