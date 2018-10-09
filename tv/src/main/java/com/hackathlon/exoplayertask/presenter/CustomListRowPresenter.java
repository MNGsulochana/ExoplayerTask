package com.hackathlon.exoplayertask.presenter;

import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.RowHeaderView;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;

import com.hackathlon.exoplayertask.R;



public class CustomListRowPresenter extends ListRowPresenter {

  public CustomListRowPresenter() {
    super();
  }

  private int mInitialSelectedPosition;

  public CustomListRowPresenter(int position) {
    this.mInitialSelectedPosition = position;
  }

  @Override
  protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
    RowHeaderView textView = holder.getHeaderViewHolder().view.findViewById(R.id.row_header);

    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

    super.onBindRowViewHolder(holder, item);
  }

  @Override
  protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
    super.initializeRowViewHolder(holder);
  }
}
