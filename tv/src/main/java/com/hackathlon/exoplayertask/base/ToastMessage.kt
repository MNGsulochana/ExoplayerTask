package com.hackathlon.exoplayertask.base

import androidx.annotation.StringRes


interface ToastMessage {
    fun message(@StringRes message: Int)

    fun message(message: String)
}
