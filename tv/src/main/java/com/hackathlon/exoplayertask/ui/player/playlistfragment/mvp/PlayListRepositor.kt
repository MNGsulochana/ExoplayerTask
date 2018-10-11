package com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.base.RepositoryImpl

class PlayListRepositor(private val apiHandler: ApiHandler) : RepositoryImpl<PlayListContract.Presenter>(), PlayListContract.Repository {
    override fun cancel() {

    }

}