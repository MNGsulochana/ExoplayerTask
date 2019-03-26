package com.hackathlon.exoplayertask.api.response

import android.content.Context
import android.util.Log
import com.hackathlon.exoplayertask.api.request.ApiIntern
import com.hackathlon.exoplayertask.ui.leanback.TestDataModel
import com.hackathlon.exoplayertask.utils.Constants.AUTH_ERROR
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

class ApiHandler(val context: Context, val api: ApiIntern, val retrofit: Retrofit) {

    private fun isJSONValid(test: String): Boolean {
        try {
            JSONObject(test)
        } catch (ex: JSONException) {

            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }

        }

        return true
    }

    private fun parseError(response: Response<*>?): ApiError {
        val converter = retrofit.responseBodyConverter<ApiError>(ApiError::class.java, arrayOfNulls(0))

        var error: ApiError? = null

        try {
            if (response?.errorBody() != null) {
                try {
                    val errorString = response.errorBody()!!.string()
                    error = if (isJSONValid(errorString)) {
                        converter.convert(response.errorBody()!!)
                    } else
                        ApiError(response.code(), errorString)
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

            }
        } catch (ignored: IOException) {
        }
        if (error == null) error = ApiError.error()
        return error
    }


    fun getTheData(success: (List<DataModel>) -> Unit,
                   authFailure: (() -> Unit)? = null,
                   error: (ApiError) -> Unit
    ):

            Call<ApiResponse<DataModel>>? {

        val call = api.getData()

        call.enqueue(object : Callback<ApiResponse<DataModel>> {
            override fun onFailure(call: Call<ApiResponse<DataModel>>, t: Throwable) {
                when (t) {
                    is SSLException, is SocketException, is SocketTimeoutException, is UnknownHostException ->
                        error(ApiError.noInternet())
                    else -> {
                        error(ApiError.error())
                    }
                }
            }

            override fun onResponse(call: Call<ApiResponse<DataModel>>, response: Response<ApiResponse<DataModel>>) {


                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        if (!apiResponse.isError) {
                            if (apiResponse.list?.isEmpty() == true)
                                error(ApiError.noData())
                            else {
                                Log.d("mytb", "" + apiResponse.data.toString())

                                success(apiResponse.list)
                                // databaseManager.saveDataToRealm(apiResponse.list)
                            }
                        } else {
                            error(apiResponse.error!!)
                        }
                    } else {
                        error(ApiError.noData())
                    }
                } else {
                    if (response.code() == AUTH_ERROR) {
                        authFailure?.invoke()
                    } else {
                        var apiError: ApiError? = null
                        if (response.errorBody() != null) apiError = parseError(response)
                        if (apiError == null)
                            error(ApiError.noData())
                    }
                }

            }
        })

        return call

    }


    fun getTheData1(limit:Int,start:Int,success: (List<TestDataModel>) -> Unit,
                   authFailure: (() -> Unit)? = null,
                   error: (ApiError) -> Unit
    ):

            Call<ApiResponse<TestDataModel>>? {

        val call = api.getData1(limit.toString(),start.toString())

        call.enqueue(object : Callback<ApiResponse<TestDataModel>> {
            override fun onFailure(call: Call<ApiResponse<TestDataModel>>, t: Throwable) {
                when (t) {
                    is SSLException, is SocketException, is SocketTimeoutException, is UnknownHostException ->
                        error(ApiError.noInternet())
                    else -> {
                        error(ApiError.error())
                    }
                }
            }

            override fun onResponse(call: Call<ApiResponse<TestDataModel>>, response: Response<ApiResponse<TestDataModel>>) {


                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        if (!apiResponse.isError) {
                            if (apiResponse.list?.isEmpty() == true)
                                error(ApiError.noData())
                            else {
                                success(apiResponse.list)
                                // databaseManager.saveDataToRealm(apiResponse.list)
                            }
                        } else {
                            error(apiResponse.error!!)
                        }
                    } else {
                        error(ApiError.noData())
                    }
                } else {
                    if (response.code() == AUTH_ERROR) {
                        authFailure?.invoke()
                    } else {
                        var apiError: ApiError? = null
                        if (response.errorBody() != null) apiError = parseError(response)
                        if (apiError == null)
                            error(ApiError.noData())
                    }
                }

            }
        })

        return call

    }

}




















