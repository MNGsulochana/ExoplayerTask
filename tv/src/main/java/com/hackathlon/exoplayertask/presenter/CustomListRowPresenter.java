package com.hackathlon.exoplayertask.presenter;

import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowHeaderView;
import androidx.leanback.widget.RowPresenter;
import android.util.TypedValue;

import com.hackathlon.exoplayertask.R;

public class CustomListRowPresenter extends ListRowPresenter {

  private int mInitialSelectedPosition;

  public CustomListRowPresenter() {
    super();
  }

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
