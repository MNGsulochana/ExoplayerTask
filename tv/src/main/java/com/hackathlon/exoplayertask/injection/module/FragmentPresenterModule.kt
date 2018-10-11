package com.hackathlon.exoplayertask.injection.module

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.db.DatabaseManager
import com.hackathlon.exoplayertask.injection.annotate.FragmentScope
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract
import com.hackathlon.exoplayertask.ui.home.mvp.HomePresenter
import com.hackathlon.exoplayertask.ui.home.mvp.HomeRepository
import com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp.PlayListContract
import com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp.PlayListPresenter
import com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp.PlayListRepositor
import dagger.Module
import dagger.Provides

@Module
class FragmentPresenterModule {

    @FragmentScope
    @Provides
    fun providesHomePresenter(presenter: HomePresenter): HomeContract.Presenter = presenter

    @FragmentScope
    @Provides
    fun providesHomeRepository(apiHandler: ApiHandler, databaseManager: DatabaseManager): HomeContract.Repository = HomeRepository(apiHandler, databaseManager)


    @FragmentScope
    @Provides
    fun providesPlayListPresenter(presenter: PlayListPresenter): PlayListContract.Presenter = presenter

    @FragmentScope
    @Provides
    fun providesPlayListRepository(apiHandler: ApiHandler): PlayListContract.Repository = PlayListRepositor(apiHandler)
}