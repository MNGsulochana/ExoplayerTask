package com.hackathlon.exoplayertask.ui.player

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hackathlon.exoplayertask.DetailsActivity
import com.hackathlon.exoplayertask.Movie
import com.hackathlon.exoplayertask.R
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.BaseActivity
import com.hackathlon.exoplayertask.injection.component.ActivityComponent
import com.hackathlon.exoplayertask.ui.player.mvp.PlayerContract

class PlayerActivity : BaseActivity<PlayerContract.Presenter>(),PlayerContract.View {

    override fun updatePlaylist() {

    }

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }



    private var mSelectedMovie: DataModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        presenter.attach(this)
        mSelectedMovie = intent.getSerializableExtra("videourl") as DataModel


        Log.d("getdataplayer",mSelectedMovie?.videourl)

    }
}
