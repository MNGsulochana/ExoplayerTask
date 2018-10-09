package com.hackathlon.exoplayertask.ui.home

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hackathlon.exoplayertask.R
import com.hackathlon.exoplayertask.base.BaseActivity
import com.hackathlon.exoplayertask.injection.component.ActivityComponent
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract

/*class HomeActivity : BaseActivity<HomeContract.Presenter>() ,HomeContract.View{

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.attach(this)
    }
}*/

class  HomeActivity :Activity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }
}