package com.hackathlon.exoplayertask.api.response

import com.hackathlon.exoplayertask.utils.Constants.DEFAULT1
import com.hackathlon.exoplayertask.utils.Constants.NO_DATA
import com.hackathlon.exoplayertask.utils.Constants.NO_INTERNET


class ApiError(val error: Int, val reason: String) {

  companion object {
    fun noInternet(): ApiError {
      return ApiError(NO_INTERNET, "No Internet connection")
    }

    fun error(): ApiError {
      return ApiError(DEFAULT1, "Please try again..")
    }

    fun noData(): ApiError {
      return ApiError(NO_DATA, "No data found")
    }
  }
}
