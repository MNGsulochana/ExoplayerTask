package com.hackathlon.exoplayertask.api.request

import com.hackathlon.exoplayertask.api.response.ApiResponse
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.api.response.ModelList
import com.hackathlon.exoplayertask.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ApiIntern {


   @GET(Constants.GET_DATA)
    fun getData() :Call<ApiResponse<DataModel>>
}