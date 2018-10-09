 package com.hackathlon.exoplayertask.ui.custom;

 import android.content.Context;
 import android.support.v17.leanback.widget.BaseCardView;
 import android.support.v17.leanback.widget.Presenter;
 import android.view.ViewGroup;

 import com.hackathlon.exoplayertask.api.response.DataModel;
 import com.hackathlon.exoplayertask.api.response.ModelList;


 public abstract class VideoAbstractCardPresenter1<T extends BaseCardView> extends Presenter {

     private static final String TAG = "MovieAbstractCardPresenter";
     private final Context mContext;

     public VideoAbstractCardPresenter1(Context context) {
         mContext = context;
     }

     public Context getContext() {
         return mContext;
     }

     @Override
     public final ViewHolder onCreateViewHolder(ViewGroup parent) {
         T cardView = onCreateView();

         return new ViewHolder(cardView);
     }


     @Override
     public final void onBindViewHolder(ViewHolder viewHolder, Object item) {
       //  MovandLangList.LangBean.MoviesBean card = (MovandLangList.LangBean.MoviesBean) item;

         ModelList lod= (ModelList) item;
         DataModel dataModel = null;
      for(int i=0;i<lod.getModelList().size();i++)
      {
          dataModel=lod.getModelList().get(i);
      }
        onBindViewHolder(lod, (T) viewHolder.view);
     }

     @Override
     public final void onUnbindViewHolder(ViewHolder viewHolder) {
         onUnbindViewHolder((T) viewHolder.view);
     }

     public void onUnbindViewHolder(T cardView) {
     }
     protected abstract T onCreateView();
     public abstract void onBindViewHolder(ModelList card, T cardView);



 }
