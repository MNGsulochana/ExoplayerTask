package com.hackathlon.exoplayertask.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.BaseCardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.hackathlon.exoplayertask.R;
import com.hackathlon.exoplayertask.api.response.DataModel;
import com.hackathlon.exoplayertask.api.response.ModelList;
import com.hackathlon.exoplayertask.ui.custom.VideoAbstractCardPresenter1;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RowCardPResenterNew extends VideoAbstractCardPresenter1<BaseCardView> {

    Realm realm;
    public RowCardPResenterNew(@NotNull Context context) {
        super(context);

    }

    @NotNull
    @Override
    protected BaseCardView onCreateView() {

        final BaseCardView cardView = new BaseCardView(getContext());
        cardView.addView(LayoutInflater.from(getContext()).inflate(R.layout.item_content, null));
        cardView.setFocusable(true);

        cardView.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        View infoView = v.findViewById(R.id.info_lay);
                        if (hasFocus) infoView.setVisibility(VISIBLE);
                        else infoView.setVisibility(GONE);
   }
                });
        return cardView;
    }

    @Override
    public void onBindViewHolder(ModelList card, BaseCardView cardView) {


        Log.d("mineapp", "nBindViewHolder: a.... " +card.getModelList().size());

    }


}
