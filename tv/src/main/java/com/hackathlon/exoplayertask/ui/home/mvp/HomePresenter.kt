package com.hackathlon.exoplayertask.ui.home.mvp

import android.content.Context
import android.widget.Toast
import com.hackathlon.exoplayertask.base.PresenterImpl
import com.hackathlon.exoplayertask.injection.annotate.ScopeContext
import javax.inject.Inject

class HomePresenter
@Inject constructor(@param:ScopeContext private val mContext: Context,
                    private val repository: HomeContract.Repository)
    : PresenterImpl<HomeContract.View>(), HomeContract.Presenter {


    override fun attach(view: HomeContract.View) {
        super.attach(view)
        view()?.run {
            init()
            fetchVideoList()
            updateContinuityAdapter(repository.fetchContinuity())
        }
    }

    override fun fetchVideoList() {
        repository.fetchVideoList(success = { list ->
            view()?.updateAdapter(list)
        }, error = { apiError ->

            message(apiError.reason)
        })
    }

    override fun message(message: Int) {
        Toast.makeText(mContext, "data : $message", Toast.LENGTH_SHORT).show()
    }

    override fun message(message: String) {
        Toast.makeText(mContext, "data : $message", Toast.LENGTH_SHORT).show()
    }

    override fun detach() {
        repository.cancel()
        super.detach()
    }
}