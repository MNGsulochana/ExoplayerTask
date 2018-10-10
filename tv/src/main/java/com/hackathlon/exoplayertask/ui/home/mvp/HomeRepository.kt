package com.hackathlon.exoplayertask.ui.home.mvp

import com.hackathlon.exoplayertask.api.response.*
import com.hackathlon.exoplayertask.base.RepositoryImpl
import com.hackathlon.exoplayertask.db.DatabaseManager
import retrofit2.Call

class HomeRepository(private val apiHandler: ApiHandler,private val databaseManager: DatabaseManager) : RepositoryImpl<HomeContract.Presenter>(),HomeContract.Repository{
   /* override fun getTheData(success: (List<DataModel>) -> Unit, authFailure: (() -> Unit)?, error: (ApiError) -> Unit, databaseManager: DatabaseManager) {
        mCall=apiHandler.getTheData(success,authFailure,error,databaseManager)
    }
*/
    private var mCall: Call<ApiResponse<DataModel>>? = null
    override fun getTheData(success: (List<DataModel>) -> Unit, authFailure: (() -> Unit)?, error: (ApiError) -> Unit) {
        mCall=apiHandler.getTheData(success,authFailure,error)

    }


    override fun cancel() {

        if(mCall!=null)
            mCall!!.cancel()

    }


}