package com.hackathlon.exoplayertask.api.response

/** Mohan on 28/10/2017.  */
class ApiResponse<RS> {

  var data: RS? = null
  var list: List<RS> = ArrayList()
  var error: ApiError? = null
  var isError = false


  internal constructor(list: ArrayList<RS>) {
    this.list = list
  }

  internal constructor(data: RS) {
    this.data = data
  }
  internal constructor(error: ApiError) {
    isError = true
    this.error = error
  }

}
