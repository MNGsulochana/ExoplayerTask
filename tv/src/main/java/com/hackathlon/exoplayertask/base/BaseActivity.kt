package com.hackathlon.exoplayertask.base

import android.app.Activity
import android.os.Bundle
import com.hackathlon.exoplayertask.injection.FireApp
import com.hackathlon.exoplayertask.injection.component.ActivityComponent
import com.hackathlon.exoplayertask.injection.module.ActivityModule
import com.hackathlon.exoplayertask.injection.module.ActivityPresenterModule
import javax.inject.Inject

abstract class BaseActivity<basePresenter : BasePresenter<*>> : Activity() {

    @Inject
    lateinit var presenter: basePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = FireApp.component().getActivityComponent(ActivityModule(this), ActivityPresenterModule())
        inject(component)
    }

    abstract fun inject(component: ActivityComponent)
}