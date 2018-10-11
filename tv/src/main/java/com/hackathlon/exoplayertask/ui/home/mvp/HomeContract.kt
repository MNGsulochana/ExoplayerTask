package com.hackathlon.exoplayertask.ui.home.mvp

import com.hackathlon.exoplayertask.api.response.ApiError
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.BasePresenter
import com.hackathlon.exoplayertask.base.BaseRepository
import com.hackathlon.exoplayertask.base.BaseView

interface HomeContract {

    interface View : BaseView {
        fun init()
        fun updateAdapter(list: List<DataModel>)
        fun updateContinuityAdapter(getcontinutyList: List<DataModel>)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchVideoList()
    }

    interface Repository : BaseRepository<Presenter> {

        fun fetchContinuity(): List<DataModel>

        fun fetchVideoList(success: (List<DataModel>) -> Unit, error: (ApiError) -> Unit)


        //  fun fetchVideoList(ApiResponseListener<>)

    }
}