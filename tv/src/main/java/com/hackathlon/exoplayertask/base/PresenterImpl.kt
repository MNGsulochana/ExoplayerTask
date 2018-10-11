package com.hackathlon.exoplayertask.base


abstract class PresenterImpl<baseView : BaseView> : BasePresenter<baseView>, ToastMessage {

    private var mBv: baseView? = null

    override fun attach(view: baseView) {
        mBv = view
    }

    override fun view(): baseView? {
        return mBv
    }
    /*
     override fun isViewAttached(): Boolean {
       return mBv != null
     }*/

    override fun detach() {
        mBv = null
    }

    abstract override fun message(message: Int)

    abstract override fun message(message: String)

    class ViewNotAttachedException : RuntimeException("BaseView not attached to View.")

}

