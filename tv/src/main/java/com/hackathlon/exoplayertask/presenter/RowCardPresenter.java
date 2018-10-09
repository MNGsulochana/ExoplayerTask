package com.hackathlon.exoplayertask.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathlon.exoplayertask.R;
import com.hackathlon.exoplayertask.api.response.DataModel;
import com.hackathlon.exoplayertask.api.response.ModelList;


import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class RowCardPresenter extends Presenter {

  private Context context;
  ArrayList<ModelList> lis;

  public RowCardPresenter(Context context) {
    this.context = context;

  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
    view.setFocusable(true);
    view.setOnFocusChangeListener(
        new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
            View infoView = v.findViewById(R.id.info_lay);
            if (hasFocus) infoView.setVisibility(VISIBLE);
            else infoView.setVisibility(GONE);
          }
        });
    return new ViewHolder(view);

  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    DataModel dataModel= (DataModel) item;
   display(dataModel.getDescp(),dataModel.getImage(),dataModel.getVideourl());





  }

  private void display(String descp, String image, String videourl) {


  }

  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {}



}
