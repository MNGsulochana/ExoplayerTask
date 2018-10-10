package com.hackathlon.exoplayertask.ui.player.mvp

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.base.RepositoryImpl
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract

class PlayerRepository (private val apiHandler: ApiHandler) : RepositoryImpl<PlayerContract.Presenter>(), PlayerContract.Repositor {
    override fun cancel() {

    }
}