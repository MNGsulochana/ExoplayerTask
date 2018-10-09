package com.hackathlon.exoplayertask.base

interface BaseRepository<basePresenter : BasePresenter<*>> {
    fun cancel();
}