package com.hackathlon.exoplayertask.ui.player.mvp

import android.content.Context
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.PresenterImpl
import com.hackathlon.exoplayertask.injection.annotate.ScopeContext
import javax.inject.Inject

class PlayerPresenter
@Inject constructor(@param:ScopeContext private val mContext: Context, private val repository: PlayerContract.Repositor) : PresenterImpl<PlayerContract.View>(), PlayerContract.Presenter {

    override fun attach(view: PlayerContract.View) {
        super.attach(view)
    }

    override fun getData(id: String): DataModel? {
        return repository.getData(id)
    }

    override fun getDataList(): List<DataModel> {
        return repository.getDataList()
    }

    override fun saveSeekTime(id: String, position: Long) {
        repository.saveSeekTime(id, position)
    }

    override fun getSeekTime(id: String): Long {
        return repository.getSeekTime(id)
    }

    override fun message(message: Int) {

    }

    override fun message(message: String) {

    }

}
