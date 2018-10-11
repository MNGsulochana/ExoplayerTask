package com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp

import com.hackathlon.exoplayertask.base.BasePresenter
import com.hackathlon.exoplayertask.base.BaseRepository
import com.hackathlon.exoplayertask.base.BaseView

interface PlayListContract {
    interface View : BaseView
    interface Presenter : BasePresenter<View>
    interface Repository : BaseRepository<Presenter>
}