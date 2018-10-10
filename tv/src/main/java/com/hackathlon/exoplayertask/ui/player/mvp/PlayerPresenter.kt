package com.hackathlon.exoplayertask.ui.player.mvp

import android.content.Context
import com.hackathlon.exoplayertask.base.PresenterImpl
import com.hackathlon.exoplayertask.injection.annotate.ScopeContext
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract
import javax.inject.Inject

class PlayerPresenter
@Inject constructor(@param:ScopeContext private val mContext: Context, private val repository: PlayerContract.Repositor) : PresenterImpl<PlayerContract.View>(), PlayerContract.Presenter
{


    override fun attach(view: PlayerContract.View) {
        super.attach(view)
        view()?.updatePlaylist()
    }


    override fun message(message: Int) {

    }

    override fun message(message: String) {

    }

}
