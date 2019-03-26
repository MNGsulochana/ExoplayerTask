
package com.hackathlon.exoplayertask.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.ImageCardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackathlon.exoplayertask.R;
import com.hackathlon.exoplayertask.ui.leanback.TestDataModel;

/**
 * The type Character card view.
 */
public class CharacterCardView extends BaseCardView {
    private Drawable mDefaultCardImage;
    private int mSelectedBackgroundColor = -1;
    private int mDefaultBackgroundColor = -1;
    /**
     * The Type.
     */
    String type;

    /**
     * Instantiates a new Character card view.
     *
     * @param context the context
     * @param type    the type
     */
    public CharacterCardView(Context context, String type) {
        super(context, null, R.style.CharacterCardStyle);
        mDefaultBackgroundColor =
                ContextCompat.getColor(getContext(), R.color.default_background);
        mSelectedBackgroundColor =
                ContextCompat.getColor(getContext(), R.color.selected_background);
        mDefaultCardImage = getContext().getResources().getDrawable(R.drawable.movie, null);
       LayoutInflater.from(getContext()).inflate(R.layout.cardlayout, this);
       this.type=type;
       setFocusable(true);
        setFocusableInTouchMode(true);
    //    updateCardBackgroundColor(v,false);

        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ImageView mainImage = (ImageView) findViewById(R.id.main_image);
               // View container = findViewById(R.id.container);
                LinearLayout layout=findViewById(R.id.infolayout);
                if (hasFocus) {
                  //  container.setBackgroundResource(R.drawable.character_focused);
                    //mainImage.setBackgroundResource(R.drawable.character_focused);
                    updateCardBackgroundColor(layout,true);
                } else {

                    updateCardBackgroundColor(layout,false);
                   // container.setBackgroundResource(R.drawable.character_not_focused_padding);
                    //mainImage.setBackgroundResource(R.drawable.character_not_focused);
                }
            }
        });
       // setFocusable(true);
    }

    private void updateCardBackgroundColor(View view, boolean selected) {
        int color = selected ? mSelectedBackgroundColor : mDefaultBackgroundColor;

        // Both background colors should be set because the view's
        // background is temporarily visible during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.infolayout).setBackgroundColor(color);
    }

    /*public void updateUi(DataModel card, CharacterCardView cardView) {
        TextView primaryText = (TextView) findViewById(R.id.primary_text);
        final ImageView imageView = (ImageView) findViewById(R.id.main_image);
        ProgressBar progressBar = findViewById(R.id.horizontal_progrssbar);
        Log.d("percentage",""+card.getPlayed_percentage());
        if(card.getPlayed_percentage()!=0 && type.equals("continuity")) {

            progressBar.setVisibility(VISIBLE);
            progressBar.setProgress(card.getPlayed_percentage());
        }
        else {
         progressBar.setVisibility(GONE);
        }

        primaryText.setText(card.getTitle());
        if (card.getImage() != null) {
            // Set card size from dimension resources.
            Resources res = cardView.getResources();
            int width = res.getDimensionPixelSize(R.dimen.card_width);
            int height = res.getDimensionPixelSize(R.dimen.card_height);
            cardView.setMeasuredDimension(width, height);



         //   RequestOptions           .apply(new RequestOptions().placeholder(mDefaultCardImage).centerCrop())
            //
            Glide.with(cardView.getContext())
                    .load(card.getImage())
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageView);
        }
    }*/


    /**
     * Update ui.
     *
     * @param card     the card
     * @param cardView the card view
     */
    public void updateUi(TestDataModel card, CharacterCardView cardView) {
        TextView primaryText = (TextView) findViewById(R.id.primary_text);
        primaryText.setText(card.getTitle());

    }

    /*public void updateBackGroundColor(CharacterCardView cardView,boolean selected) {

        int color = selected ? mSelectedBackgroundColor : mDefaultBackgroundColor;

        // Both background colors should be set because the view's
        // background is temporarily visible during animations.
        cardView.setBackgroundColor(color);
        cardView.findViewById(R.id.info_field).setBackgroundColor(color);

    }*/
}
