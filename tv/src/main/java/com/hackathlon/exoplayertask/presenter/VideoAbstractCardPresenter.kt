package com.hackathlon.exoplayertask.presenter

import android.content.Context
import android.support.v17.leanback.widget.BaseCardView
import android.support.v17.leanback.widget.Presenter
import android.view.ViewGroup
import com.hackathlon.exoplayertask.api.response.DataModel


abstract class VideoAbstractCardPresenter<T : BaseCardView>(val context: Context) : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val cardView = onCreateView()

        return Presenter.ViewHolder(cardView)
    }


    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        var card: DataModel = item as DataModel


        onBindViewHolder(card, viewHolder.view as T)
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        onUnbindViewHolder(viewHolder.view as T)
    }

    fun onUnbindViewHolder(cardView: T) {}
    protected abstract fun onCreateView(): T
    abstract fun onBindViewHolder(card: DataModel?, cardView: T)

    companion object {

        private val TAG = "MovieAbstractCardPresenter"
    }


}
