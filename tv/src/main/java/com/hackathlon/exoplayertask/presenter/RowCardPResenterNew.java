package com.hackathlon.exoplayertask.presenter;

import android.content.Context;
import androidx.leanback.widget.BaseCardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hackathlon.exoplayertask.R;
import com.hackathlon.exoplayertask.api.response.DataModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.realm.Realm;

public class RowCardPResenterNew extends VideoAbstractCardPresenter<BaseCardView> {

  Realm realm;

  public RowCardPResenterNew(@NotNull Context context) {
    super(context);
  }

  @NotNull
  @Override
  protected BaseCardView onCreateView() {

    final BaseCardView cardView = new BaseCardView(getContext(), null, R.style.SideInfoCardStyle);
    cardView.addView(LayoutInflater.from(getContext()).inflate(R.layout.character_card, null));
    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);

    /*     cardView.setOnFocusChangeListener(
                 new View.OnFocusChangeListener() {
                     @Override
                     public void onFocusChange(View v, boolean hasFocus) {
                         View infoView = v.findViewById(R.id.info_lay);
                         if (hasFocus) infoView.setVisibility(VISIBLE);
                         else infoView.setVisibility(GONE);
    }
                 });*/
    /*cardView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            View infoView = view.findViewById(R.id.info_lay);
            infoView.setVisibility(VISIBLE);
          }
        });*/
    return cardView;
  }

  @Override
  public void onBindViewHolder(@Nullable DataModel card, @NotNull BaseCardView cardView) {

    DataModel dataModel = (DataModel) card;
    /* if(dataModel!=null) {

        modelRealmList.add(dataModel);
        // Constants..add(dataModel);

        for (DataModel b : modelRealmList) {
            // Persist your data easily
            // databaseManager.saveDataToRealm(b);
        }
    }
    else {
        // Log.d("getdata", "" + databaseManager.hasData());

        Log.d("getdatarealmnull", "mSelectedMovie!!.image");
    }*/
    display(
        dataModel.getDescp(),
        dataModel.getImage(),
        dataModel.getVideourl(),
        dataModel.getTitle(),
        cardView);
  }

  private void display(String descp, String image_url, String videourl, String title, View view)
      throws NullPointerException {
    Log.d("getdata", "mSelectedMovie!!.image");
    if (view == null) {
      Log.d("getdatanull", "mSelectedMovie!!.image");
    }
    ImageView image = view.findViewById(R.id.main_image);

    TextView txtTitle = view.findViewById(R.id.primary_text);
    if (!TextUtils.isEmpty(title)) txtTitle.setText(title);
    if (!TextUtils.isEmpty(image_url)) Glide.with(view.getContext()).load(image_url).into(image);
  }
}
