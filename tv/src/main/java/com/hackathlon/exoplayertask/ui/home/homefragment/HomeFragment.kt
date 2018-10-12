package com.hackathlon.exoplayertask.ui.home.homefragment


import android.content.Intent
import android.os.Bundle
import android.support.v17.leanback.widget.*
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.base.BaseRowFragment
import com.hackathlon.exoplayertask.injection.component.FragmentComponent
import com.hackathlon.exoplayertask.presenter.CardPresenterNew
import com.hackathlon.exoplayertask.presenter.CharacterCardPresenter
import com.hackathlon.exoplayertask.presenter.CustomListRowPresenter
import com.hackathlon.exoplayertask.presenter.RowCardPResenterNew
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract
import com.hackathlon.exoplayertask.ui.player.PlayerActivity
import com.hackathlon.exoplayertask.ui.player.model.Playercontinuity


class HomeFragment : BaseRowFragment<HomeContract.Presenter>(), HomeContract.View {

    lateinit var listRow: ListRow
    lateinit var currentRowAdapter: ArrayObjectAdapter
    lateinit var rowsAdapter: ArrayObjectAdapter


    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun init() {
        rowsAdapter = ArrayObjectAdapter(CustomListRowPresenter())
        adapter = rowsAdapter


        onItemViewSelectedListener = OnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->

            if (item != null) {
                var posterUrl: String? = null
                if (item is DataModel && (item as DataModel).image != null) {
                    val dataModel = item
                    posterUrl = dataModel.image
                } else {
                    if (item is Playercontinuity && (item as Playercontinuity).videourl_cont != null) {

                    }
                }
            }
        }
        onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->

            if (item != null) {

                val intent = Intent(activity, PlayerActivity::class.java)
                intent.putExtra(PlayerActivity.ITEM_ID, (item as DataModel).id)
                activity.startActivity(intent)

//                listRow = row as ListRow
//
//                currentRowAdapter = listRow.getAdapter() as ArrayObjectAdapter
//                val selectedIndex: Int = currentRowAdapter.indexOf(item)
//                Log.d("helloindex", "onItemSelected: " + selectedIndex)
//
//
//                if (selectedIndex != -1 && (currentRowAdapter.size() - 1) == selectedIndex) {
//                    val c = currentRowAdapter.size() + selectedIndex
//                    // The last item was selected
//                    Log.d("hello", "onItemSelected: " + c)
//
//                }
                /* if (item is DataModel) {


 //                    intent.putExtra("selectdindex", selectedIndex + 1)
 //                    intent.putExtra("headertype", "normal")


                 } else if (item is Playercontinuity) {
                     // startPlayerActivity(item, selectedIndex + 1, "continue")
                     val intent = Intent(activity, PlayerActivity::class.java)
                     intent.putExtra("videourl", item)
                     intent.putExtra("selectdindex", selectedIndex + 1)
                     intent.putExtra("headertype", "continue")
                     activity.startActivity(intent)
                 }*/

            }

        }
    }

    override fun updateAdapter(list: List<DataModel>) {

        if (list.isNotEmpty()) {
            val mGridPresenter = CardPresenterNew()
            val gridRowAdapter = ArrayObjectAdapter(mGridPresenter)
            for (i in list) {
                gridRowAdapter.add(i)
            }
            val headerItem = HeaderItem(0, "VIDEO URL")
            rowsAdapter.add(ListRow(headerItem, gridRowAdapter))
        }
    }




    override fun updateContinuityAdapter(getcontinutyList: List<DataModel>) {
        if (getcontinutyList.isNotEmpty()) {


           // val grid1 = CardPresenterNew()
           // val grid1=RowCardPResenterNew(activity)
            val grid1=CharacterCardPresenter(activity)

            val gridRowAdapter = ArrayObjectAdapter(grid1)
            for (j in getcontinutyList) {
                gridRowAdapter.add(j)
            }
            val headerItem1 = HeaderItem(1, "CONTINUE WATCHING")
            rowsAdapter.add(ListRow(headerItem1, gridRowAdapter))
        }
    }

    /*private inner class GridItemPresenter : Presenter() {

        override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
            val view = TextView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(200, 300)
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            view.setBackgroundColor(ContextCompat.getColor(activity, R.color.default_background))
            view.setTextColor(Color.WHITE)
            view.gravity = Gravity.CENTER
            return Presenter.ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {

            // val dataModel =

            (viewHolder.view as TextView).text = item as String
        }

        override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {}
    }*/


}
