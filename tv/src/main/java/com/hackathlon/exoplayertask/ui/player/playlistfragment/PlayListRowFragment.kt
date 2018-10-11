package com.hackathlon.exoplayertask.ui.player.playlistfragment


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v17.leanback.widget.*
import android.view.View
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.BaseRowFragment1
import com.hackathlon.exoplayertask.injection.component.FragmentComponent
import com.hackathlon.exoplayertask.presenter.CustomListRowPresenter
import com.hackathlon.exoplayertask.presenter.RowCardPresenter
import com.hackathlon.exoplayertask.ui.player.playlistfragment.mvp.PlayListContract
import com.hackathlon.exoplayertask.utils.Constants
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayListRowFragment : BaseRowFragment1<PlayListContract.Presenter>(), PlayListContract.View,
        OnItemViewClickedListener, OnItemViewSelectedListener {


    var playlist: ArrayList<DataModel>? = null
    var rowsadapter: ArrayObjectAdapter? = null
    var updatePlaylsit: updateThePlaylist? = null
    var isVisited = false
    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }


    open fun newInstance(listener: ArrayList<DataModel>): PlayListRowFragment {
        val fragment = PlayListRowFragment()
        val args = Bundle()
        args.putSerializable(Constants.PLAYLIST, listener)
        fragment.setArguments(args)
        return fragment
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        updatePlaylsit = activity as updateThePlaylist
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.requestFocus()
        if (arguments != null) {
            playlist = arguments.getSerializable(Constants.PLAYLIST) as ArrayList<DataModel>
        }
        setupEventListeners()
        loadPlaylist()
    }

    private fun setupEventListeners() {
        setOnItemViewClickedListener(this)
        setOnItemViewSelectedListener(this)
    }

    private fun loadPlaylist() {

        if (playlist != null) {
            rowsadapter = ArrayObjectAdapter(CustomListRowPresenter())
            val header = HeaderItem(0, Constants.RELATED)
            val listRowAdapter = ArrayObjectAdapter(RowCardPresenter(activity))
            for (j in playlist?.indices!!) {
                listRowAdapter.add(playlist?.get(j))
            }
            rowsadapter?.add(ListRow(header, listRowAdapter))
            adapter = rowsadapter
        }
    }


    open interface updateThePlaylist {
        fun contentFocusedClicked(`object`: DataModel, clicked: Boolean?)
    }

    override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {
        if (item != null) {
            if (item is DataModel) run { updatePlaylsit?.contentFocusedClicked(item as DataModel, true) }
        }


    }

    override fun onItemSelected(itemViewHolder: Presenter.ViewHolder?, item: Any?, rowViewHolder: RowPresenter.ViewHolder?, row: Row?) {

    }

}
