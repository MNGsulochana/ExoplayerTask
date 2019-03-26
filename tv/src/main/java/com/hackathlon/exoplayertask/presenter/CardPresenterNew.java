package com.hackathlon.exoplayertask.presenter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;
import androidx.core.content.ContextCompat;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hackathlon.exoplayertask.R;
import com.hackathlon.exoplayertask.api.response.DataModel;

public class CardPresenterNew extends Presenter {

  private int mSelectedBackgroundColor = -1;
  private int mDefaultBackgroundColor = -1;
  private Drawable mDefaultCardImage;

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    mDefaultBackgroundColor =
        ContextCompat.getColor(parent.getContext(), R.color.default_background);
    mSelectedBackgroundColor =
        ContextCompat.getColor(parent.getContext(), R.color.selected_background);
    mDefaultCardImage = parent.getResources().getDrawable(R.drawable.movie, null);

    ImageCardView cardView =
        new ImageCardView(parent.getContext()) {
          @Override
          public void setSelected(boolean selected) {
            updateCardBackgroundColor(this, selected);
            super.setSelected(selected);
          }
        };

    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);
   updateCardBackgroundColor(cardView, false);
    return new ViewHolder(cardView);
  }

  private void updateCardBackgroundColor(ImageCardView view, boolean selected) {
    int color = selected ? mSelectedBackgroundColor : mDefaultBackgroundColor;

    // Both background colors should be set because the view's
    // background is temporarily visible during animations.
    view.setBackgroundColor(color);
    view.findViewById(R.id.info_field).setBackgroundColor(color);
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {

    ImageCardView cardView = (ImageCardView) viewHolder.view;

    if (item instanceof DataModel) {
      DataModel dataModel = (DataModel) item;
      setdatatoView(cardView, dataModel.getTitle(), dataModel.getImage());
    } /*else if (item instanceof Playercontinuity) {

        Playercontinuity payercontn = (Playercontinuity) item;
        setdatatoView(cardView, payercontn.getTitle_contn(), payercontn.getImage());
      }*/
  }

  private void setdatatoView(ImageCardView cardView, String title, String image) {

    cardView.setTitleText(title);


    if (image != null) {
      // Set card size from dimension resources.
      Resources res = cardView.getResources();
      int width = res.getDimensionPixelSize(R.dimen.card_width);
      int height = res.getDimensionPixelSize(R.dimen.card_height);
      cardView.setMainImageDimensions(width, height);

      Glide.with(cardView.getContext())
          .load(image)
          .apply(RequestOptions.errorOf(mDefaultCardImage))
          .into(cardView.getMainImageView());
    }
  }

  @Override
  public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    ImageCardView cardView = (ImageCardView) viewHolder.view;

    // Remove references to images so that the garbage collector can free up memory.
    cardView.setBadgeImage(null);
    cardView.setMainImage(null);
  }
}
