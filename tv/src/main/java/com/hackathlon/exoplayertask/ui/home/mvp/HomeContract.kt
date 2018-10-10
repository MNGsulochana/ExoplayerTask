package com.hackathlon.exoplayertask.ui.home.mvp

import android.view.View
import com.hackathlon.exoplayertask.api.response.ApiError
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.api.response.ModelList
import com.hackathlon.exoplayertask.base.BasePresenter
import com.hackathlon.exoplayertask.base.BaseRepository
import com.hackathlon.exoplayertask.base.BaseView
import com.hackathlon.exoplayertask.db.DatabaseManager

interface HomeContract {
    interface View : BaseView
    {
        fun init()
        fun updateAdapter(list :List<DataModel>)
    }
    interface Presenter : BasePresenter<View>
    {
        fun getTheData()
    }
    interface Repository : BaseRepository<Presenter>
    {

        fun getTheData(success: (List<DataModel>) -> Unit, authFailure: (() -> Unit)? = null,
                       error: (ApiError) -> Unit)

    }
}