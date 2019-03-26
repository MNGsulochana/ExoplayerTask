package com.hackathlon.exoplayertask.api.request

import com.hackathlon.exoplayertask.api.response.ApiResponse
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.ui.leanback.TestDataModel
import com.hackathlon.exoplayertask.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiIntern {


    @GET(Constants.GET_DATA)
    fun getData(): Call<ApiResponse<DataModel>>


    @GET(Constants.GET_DATA)
    fun getData1(@Query("_limit") limit: String?,
                 @Query("_start") start: String?): Call<ApiResponse<TestDataModel>>
}