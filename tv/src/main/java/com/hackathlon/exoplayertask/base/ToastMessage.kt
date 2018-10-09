package com.hackathlon.exoplayertask.base

import android.support.annotation.StringRes


interface ToastMessage {
  fun message(@StringRes message: Int)

  fun message(message: String)
}
