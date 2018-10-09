package com.hackathlon.exoplayertask.base

interface BasePresenter<baseview : BaseView> {

    //fun isViewAttached(): Boolean

    fun attach(view: baseview)

    fun view(): baseview?

    fun detach()
}