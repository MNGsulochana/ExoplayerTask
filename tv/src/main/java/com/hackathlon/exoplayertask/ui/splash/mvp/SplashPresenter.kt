package com.hackathlon.exoplayertask.ui.splash.mvp

import android.os.Handler
import com.hackathlon.exoplayertask.base.PresenterImpl
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val repository: SplashContract.Repository) : PresenterImpl<SplashContract.View>(), SplashContract.Presenter {


    override fun startTimer(view: SplashContract.View) {
        Handler().postDelayed(Runnable { view.callMainPAge() }, SPLASH_TIMEOUT)
    }


    override fun attach(view: SplashContract.View) {
        super.attach(view)
        //view.callMainPAge()
        startTimer(view)
    }


    override fun message(message: Int) {

    }

    override fun message(message: String) {
    }

    companion object {
        const val SPLASH_TIMEOUT: Long = 1500
    }
}