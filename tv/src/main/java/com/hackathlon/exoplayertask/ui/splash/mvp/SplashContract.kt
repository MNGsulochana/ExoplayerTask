package com.hackathlon.exoplayertask.ui.splash.mvp

import com.hackathlon.exoplayertask.base.BasePresenter
import com.hackathlon.exoplayertask.base.BaseRepository
import com.hackathlon.exoplayertask.base.BaseView

interface SplashContract {
    interface View : BaseView {
        fun callMainPAge()
    }

    interface Presenter : BasePresenter<View> {
        fun startTimer(view: View)
    }

    interface Repository : BaseRepository<Presenter>
}