package com.hackathlon.exoplayertask.injection.component


import com.hackathlon.exoplayertask.injection.FireApp
import com.hackathlon.exoplayertask.injection.module.*

import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, ApiModule::class])
@Singleton
interface AppComponent {

    fun getActivityComponent(activityModule: ActivityModule, activityPresenterModule: ActivityPresenterModule): ActivityComponent
    fun getFragmentcomponent(fragmentModule: FragmentModule, fragmentPresenterModule: FragmentPresenterModule): FragmentComponent

    fun inject(fireApp: FireApp)
}