package com.hackathlon.exoplayertask.ui.splash.mvp

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.base.RepositoryImpl

class SplashRepository(private val apiHandler: ApiHandler) : RepositoryImpl<SplashContract.Presenter>(), SplashContract.Repository {

    override fun cancel() {

    }
}