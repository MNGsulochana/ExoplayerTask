package com.hackathlon.exoplayertask.ui.home.mvp

import com.hackathlon.exoplayertask.api.response.ApiError
import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.api.response.ApiResponse
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.RepositoryImpl
import com.hackathlon.exoplayertask.db.DatabaseManager
import io.realm.RealmResults
import retrofit2.Call

class HomeRepository
(private val apiHandler: ApiHandler, private val databaseManager: DatabaseManager)
    : RepositoryImpl<HomeContract.Presenter>(), HomeContract.Repository {
   /* override fun getPercentageData(): RealmResults<DataModel>? {
        return databaseManager.getPlayedPercentage()
    }*/


    private var mCall: Call<ApiResponse<DataModel>>? = null

    override fun fetchContinuity(): List<DataModel> {
        return databaseManager.getContinuityList()
    }

    override fun fetchVideoList(success: (List<DataModel>) -> Unit, error: (ApiError) -> Unit) {

        mCall = apiHandler.getTheData(success = { list ->

            databaseManager.saveDataToRealm(list)

            success(databaseManager.getDataList())
        }, error = { apiError ->
            success(databaseManager.getDataList())
            error(apiError)
        })
    }

    override fun cancel() {
        if (mCall != null)
            mCall!!.cancel()
    }
}


/*override fun fetchVideoList(success: (List<DataModel>) -> Unit, authFailure: (() -> Unit)?, error: (ApiError) -> Unit) {
    mCall=apiHandler.fetchVideoList(success,authFailure,error)

}*/





