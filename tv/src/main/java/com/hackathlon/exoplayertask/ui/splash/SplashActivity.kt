package com.hackathlon.exoplayertask.ui.splash

import android.content.Intent
import android.os.Bundle
import com.hackathlon.exoplayertask.R
import com.hackathlon.exoplayertask.base.BaseActivity
import com.hackathlon.exoplayertask.injection.component.ActivityComponent
import com.hackathlon.exoplayertask.ui.home.HomeActivity
import com.hackathlon.exoplayertask.ui.leanback.LeanBackActivity

import com.hackathlon.exoplayertask.ui.splash.mvp.SplashContract

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View {
    override fun callMainPAge() {

       /* val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)*/

       val intent = Intent(this@SplashActivity, LeanBackActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        presenter.attach(this)
    }


}
