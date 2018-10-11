package com.hackathlon.exoplayertask.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.Presenter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hackathlon.exoplayertask.R;
import com.hackathlon.exoplayertask.api.response.DataModel;
import com.hackathlon.exoplayertask.db.DatabaseManager;

import io.realm.RealmList;

import static android.view.View.VISIBLE;

public class RowCardPresenter extends Presenter {

  RealmList<DataModel> modelRealmList = new RealmList<>();
  private Context context;
  private DatabaseManager databaseManager;

  //                 val playlistmodel: ArrayList<DataModel> = ArrayList()

  public RowCardPresenter(Context context) {
    this.context = context;
    //      databaseManager.getRealm();

  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {

    final BaseCardView cardView = new BaseCardView(context, null, R.style.SideInfoCardStyle);
    // cardView.addView();
    // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, null,
    // false);

    //  View view=LayoutInflater.inflate(R.layout.item_content, parent);
    View view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false);

    // View view=LayoutInflater.from(context).inflate(R.layout.item_content,null);

    /* if(view.getParent()!=null)
    ((ViewGroup)view.getParent()).removeView(view);*/
    // cardView.addView(view);
    /*  view.setFocusable(true);
    view.setOnFocusChangeListener(
        new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
            View infoView = v.findViewById(R.id.info_lay);
            if (hasFocus) infoView.setVisibility(VISIBLE);
            else infoView.setVisibility(GONE);
          }
        });*/
    view.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            View infoView = view.findViewById(R.id.info_lay);
            infoView.setVisibility(VISIBLE);
          }
        });
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    DataModel dataModel = (DataModel) item;
    if (dataModel != null) {

      modelRealmList.add(dataModel);
      // Constants..add(dataModel);

      for (DataModel b : modelRealmList) {
        // Persist your data easily
        // databaseManager.saveDataToRealm(b);
      }
    } else {
      // Log.d("getdata", "" + databaseManager.hasData());

      Log.d("getdatarealmnull", "mSelectedMovie!!.image");
    }
    display(
        dataModel.getDescp(),
        dataModel.getImage(),
        dataModel.getVideourl(),
        dataModel.getTitle(),
        viewHolder.view);
  }

  private void display(String descp, String image_url, String videourl, String title, View view)
      throws NullPointerException {
    Log.d("getdata", "mSelectedMovie!!.image");
    if (view == null) {
      Log.d("getdatanull", "mSelectedMovie!!.image");
    }
    ImageView image = view.findViewById(R.id.posterimage);

    TextView txtTitle = view.findViewById(R.id.txtTitle);
    if (!TextUtils.isEmpty(title)) txtTitle.setText(title);
    if (!TextUtils.isEmpty(image_url)) Glide.with(view.getContext()).load(image_url).into(image);
  }

  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {}
}
