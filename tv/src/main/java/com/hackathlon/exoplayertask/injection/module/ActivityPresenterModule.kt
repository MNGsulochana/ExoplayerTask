package com.hackathlon.exoplayertask.injection.module

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.db.DatabaseManager
import com.hackathlon.exoplayertask.injection.annotate.ActivityScope
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract
import com.hackathlon.exoplayertask.ui.home.mvp.HomePresenter
import com.hackathlon.exoplayertask.ui.home.mvp.HomeRepository
import com.hackathlon.exoplayertask.ui.player.mvp.PlayerContract
import com.hackathlon.exoplayertask.ui.player.mvp.PlayerPresenter
import com.hackathlon.exoplayertask.ui.player.mvp.PlayerRepository
import com.hackathlon.exoplayertask.ui.splash.mvp.SplashContract
import com.hackathlon.exoplayertask.ui.splash.mvp.SplashPresenter
import com.hackathlon.exoplayertask.ui.splash.mvp.SplashRepository
import dagger.Module
import dagger.Provides

@Module
class ActivityPresenterModule {

    @ActivityScope
    @Provides
    fun providesSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter = presenter

    @ActivityScope
    @Provides
    fun providesSplashRepository(apiHandler: ApiHandler): SplashContract.Repository = SplashRepository(apiHandler)


    @ActivityScope
    @Provides
    fun providesHomeRepository(apiHandler: ApiHandler, databaseManager: DatabaseManager): HomeContract.Repository = HomeRepository(apiHandler, databaseManager)

    @ActivityScope
    @Provides
    fun providesHomePresenter(presenter: HomePresenter): HomeContract.Presenter = presenter

    @ActivityScope
    @Provides
    fun providesPlayerPresenter(presenter: PlayerPresenter): PlayerContract.Presenter = presenter

    @ActivityScope
    @Provides
    fun providesPlayerRepository(apiHandler: ApiHandler, databaseManager: DatabaseManager): PlayerContract.Repositor = PlayerRepository(apiHandler, databaseManager)


    /*

       @ActivityScope
       @Provides
       fun providesHomeRepository(apiHandler: ApiHandler) : HomeContract.Repository = HomeRepository(apiHandler)*/
}