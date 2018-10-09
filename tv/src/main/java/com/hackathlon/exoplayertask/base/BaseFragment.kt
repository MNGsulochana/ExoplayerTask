package com.hackathlon.exoplayertask.base

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackathlon.exoplayertask.injection.FireApp
import com.hackathlon.exoplayertask.injection.component.FragmentComponent
import com.hackathlon.exoplayertask.injection.module.FragmentModule
import com.hackathlon.exoplayertask.injection.module.FragmentPresenterModule
import javax.inject.Inject

abstract class BaseFragment<basePresenter  : BasePresenter<*>> : android.support.v4.app.Fragment()
{
    @Inject
    lateinit var presenter: basePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component=FireApp.component().getFragmentcomponent(FragmentModule(context!!), FragmentPresenterModule())

        inject(component)
    }

    abstract fun inject(component: FragmentComponent)
}