package com.hackathlon.exoplayertask.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.leanback.app.BrowseFragment
import androidx.leanback.app.RowsSupportFragment
import com.hackathlon.exoplayertask.injection.FireApp
import com.hackathlon.exoplayertask.injection.component.FragmentComponent
import com.hackathlon.exoplayertask.injection.module.FragmentModule
import com.hackathlon.exoplayertask.injection.module.FragmentPresenterModule
import javax.inject.Inject

abstract class BaseRowFragment<basePresenter : BasePresenter<*>> : RowsSupportFragment() {

    @Inject
    lateinit var presenter: basePresenter


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = FireApp.component().getFragmentcomponent(FragmentModule(context!!), FragmentPresenterModule())
        inject(component)
    }

    abstract fun inject(component: FragmentComponent)


}