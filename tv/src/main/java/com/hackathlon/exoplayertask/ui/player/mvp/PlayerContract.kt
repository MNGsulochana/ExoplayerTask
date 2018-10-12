package com.hackathlon.exoplayertask.ui.player.mvp

import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.BasePresenter
import com.hackathlon.exoplayertask.base.BaseRepository
import com.hackathlon.exoplayertask.base.BaseView

interface PlayerContract {
    interface View : BaseView

    interface Presenter : BasePresenter<View> {
        fun getData(id: String): DataModel?
        fun getDataList(): List<DataModel>
        fun getSeekTime(id: String): Long
        fun saveSeekTime(id: String, position: Long)
    }

    interface Repositor : BaseRepository<Presenter> {

        fun getData(id: String): DataModel?
        fun getDataList(): List<DataModel>
        fun saveSeekTime(id: String, position: Long)
        fun getSeekTime(id: String): Long

    }
}