package com.hackathlon.exoplayertask.base


abstract class RepositoryImpl<basePresenter : BasePresenter<*>> : BaseRepository<basePresenter> {
    abstract override fun cancel()
}
