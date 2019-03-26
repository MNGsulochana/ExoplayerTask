package com.hackathlon.exoplayertask.base

import android.os.Bundle
import com.hackathlon.exoplayertask.injection.FireApp
import com.hackathlon.exoplayertask.injection.component.FragmentComponent
import com.hackathlon.exoplayertask.injection.module.FragmentModule
import com.hackathlon.exoplayertask.injection.module.FragmentPresenterModule
import javax.inject.Inject

abstract class BaseFragment<basePresenter : BasePresenter<*>> : androidx.fragment.app.Fragment() {
    @Inject
    lateinit var presenter: basePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = FireApp.component().getFragmentcomponent(FragmentModule(context!!), FragmentPresenterModule())

        inject(component)
    }

    abstract fun inject(component: FragmentComponent)
}