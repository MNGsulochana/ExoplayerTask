package com.hackathlon.exoplayertask.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.widget.ImageCardView;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class AspectRatioImageView extends ImageView {
  public AspectRatioImageView(@NonNull Context context) {
    super(context);
  }

  public AspectRatioImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public AspectRatioImageView(
          @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = width * 9 / 16;
    setMeasuredDimension(width, height);
  }
}
