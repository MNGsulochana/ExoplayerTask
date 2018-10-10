package com.hackathlon.exoplayertask.ui.player.mvp

import com.hackathlon.exoplayertask.base.BasePresenter
import com.hackathlon.exoplayertask.base.BaseRepository
import com.hackathlon.exoplayertask.base.BaseView

interface PlayerContract {
    interface View : BaseView

        {
            fun updatePlaylist()
        }

    interface  Presenter : BasePresenter<View>

    interface Repositor : BaseRepository<Presenter>
}