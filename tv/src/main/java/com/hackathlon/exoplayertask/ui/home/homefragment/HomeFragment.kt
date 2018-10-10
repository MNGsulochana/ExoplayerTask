package com.hackathlon.exoplayertask.ui.home.homefragment


//import com.hackathlon.exoplayertask.ui.home.homefragment.mvp.HomeFragmentContract
import android.app.Fragment
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v17.leanback.widget.*
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.hackathlon.exoplayertask.DetailsActivity
import com.hackathlon.exoplayertask.MainActivity
import com.hackathlon.exoplayertask.R
import com.hackathlon.exoplayertask.api.response.DataModel
import com.hackathlon.exoplayertask.api.response.ModelList
import com.hackathlon.exoplayertask.base.BaseRowFragment
import com.hackathlon.exoplayertask.injection.component.FragmentComponent
import com.hackathlon.exoplayertask.presenter.CustomListRowPresenter
import com.hackathlon.exoplayertask.presenter.RowCardPresenter
import com.hackathlon.exoplayertask.ui.home.mvp.HomeContract
import com.hackathlon.exoplayertask.ui.player.PlayerActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */


class HomeFragment : BaseRowFragment<HomeContract.Presenter>(), HomeContract.View {

    var  j : Int = 0

   lateinit var listRow : ListRow
     lateinit var currentRowAdapter :ArrayObjectAdapter
    override fun updateAdapter(list: List<DataModel>) {

        if(list.size >0)
        {

            Log.d("mylist",""+list.get(0).descp)
            val mGridPresenter = RowCardPresenter(activity)
            //val mGridPresenter = RowCardPResenterNew(activity)
            gridRowAdapter = ArrayObjectAdapter(mGridPresenter)
            val headerItem = HeaderItem("VIDEO URL")
            for(i in list)
            {
                gridRowAdapter.add(i)



            }
            rowsAdapter.add(ListRow(headerItem, gridRowAdapter))



        }
    }

    override fun init() {
        rowsAdapter = ArrayObjectAdapter(CustomListRowPresenter())
        adapter=rowsAdapter
        onItemViewSelectedListener = OnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            // TODO Pagination logic writern here uncomment to use it AND CALL NEXT PAGE
            /*     final ListRow listRow = (ListRow) row;
            final ArrayObjectAdapter currentRowAdapter = (ArrayObjectAdapter) listRow.getAdapter();
            int selectedIndex = currentRowAdapter.indexOf(item);
            if (selectedIndex != -1 && (currentRowAdapter.size() - 1) == selectedIndex) {
              // The last item was selected
              Log.d("hello", currentRowAdapter.size() + "onItemSelected: " + selectedIndex);

            }*/



            if (item != null) {
                var posterUrl: String? = null
            if(item is DataModel && (item as DataModel).image!=null )
            {
                val dataModel = item
                posterUrl=dataModel.image
            }



               /* if (posterChangeCallback != null) {
                    posterChangeCallback.changePoster(posterUrl)
                }*/
            }
        }
        onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->

            if(item !=null)
            {

                listRow = row as ListRow

                currentRowAdapter = listRow.getAdapter() as ArrayObjectAdapter
                val selectedIndex: Int =currentRowAdapter.indexOf(item)


                if (selectedIndex != -1 && (currentRowAdapter.size() - 1) == selectedIndex) {
                    val c= currentRowAdapter.size()  + selectedIndex
                    // The last item was selected
                    Log.d("hello",  "onItemSelected: " + c);

                }

                if(item is DataModel)
                {

                    val intent = Intent(activity, PlayerActivity::class.java)
                    intent.putExtra("videourl",item);
                    activity.startActivity(intent)

                }


            }

            }
        }




    lateinit var rowsAdapter: ArrayObjectAdapter
    lateinit var mAdapter: ArrayObjectAdapter
    lateinit var gridRowAdapter: ArrayObjectAdapter


 /* override fun init() {
        //  mAdapter= ArrayObjectAdapter(CustomListRowPresenter())
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

    }

  override fun updateAdapter(list: List<ModelList>) {


        Log.d("mytv_ada", "" + list.size)
       *//*if (list.size > 0) {
              rowsAdapter = ArrayObjectAdapter(RowCardPresenter(activity))
            for(i in list)
            {
                Log.d("mytv_ada",""+list.size)
                rowsAdapter.add(i)
            }
            if(rowsAdapter!=null)
            {
                val headerItem = HeaderItem("VIDEO URL")
                mAdapter.add(ListRow(headerItem, rowsAdapter))
            }*//*

        val headerItem = HeaderItem("VIDEO URL")
        //val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val mGridPresenter = GridItemPresenter()
        gridRowAdapter = ArrayObjectAdapter(mGridPresenter)

        gridRowAdapter.add(resources.getString(R.string.grid_view))
        gridRowAdapter.add(getString(R.string.error_fragment))
        gridRowAdapter.add(resources.getString(R.string.personal_settings))
        rowsAdapter.add(ListRow(headerItem, gridRowAdapter))

        adapter=rowsAdapter
    }*/


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("mytv_ada", "sample")
        presenter.attach(this)
        presenter.getTheData()


    }


    private inner class GridItemPresenter : Presenter() {

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
    }


    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }


}
