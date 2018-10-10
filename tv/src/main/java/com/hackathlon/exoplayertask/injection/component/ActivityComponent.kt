package com.hackathlon.exoplayertask.injection.component

import com.hackathlon.exoplayertask.injection.annotate.ActivityScope
import com.hackathlon.exoplayertask.injection.module.ActivityModule
import com.hackathlon.exoplayertask.injection.module.ActivityPresenterModule
import com.hackathlon.exoplayertask.ui.home.HomeActivity
import com.hackathlon.exoplayertask.ui.player.PlayerActivity

import com.hackathlon.exoplayertask.ui.splash.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class, ActivityPresenterModule::class])
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: PlayerActivity)

}