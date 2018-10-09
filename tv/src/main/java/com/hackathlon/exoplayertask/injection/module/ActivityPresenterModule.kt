package com.hackathlon.exoplayertask.injection.module

import com.hackathlon.exoplayertask.api.response.ApiHandler
import com.hackathlon.exoplayertask.injection.annotate.ActivityScope
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract
import com.hackathlon.exoplayertask.ui.home.mvp.HomePresenter
import com.hackathlon.exoplayertask.ui.home.mvp.HomeRepository
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


 /* @ActivityScope
    @Provides
    fun providesHomePresenter(presenter : HomePresenter):HomeContract.Presenter =presenter

    @ActivityScope
    @Provides
    fun providesHomeRepository(apiHandler: ApiHandler) : HomeContract.Repository = HomeRepository(apiHandler)*/
}