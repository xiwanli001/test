package com.moon.razernexu.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.moon.razernexu.MainActivity;
import com.moon.razernexu.R;
import com.moon.razernexu.bean.Content;
import com.moon.razernexu.utils.BlurTransformation;
import com.moon.razernexu.utils.FontDisplayUtil;
import com.moon.razernexu.view.ImgConstraintLayout;


public class TypeSixContentPresenter extends Presenter {

    private MainActivity mContext;

    private static final String TAG = "TypeSixContentPresenter";

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = (MainActivity)parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_six_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
//        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                viewHolder.mTvDesc.setSelected(hasFocus);
//                if (hasFocus) {
//                    viewHolder.mTvDesc.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
//                } else {
//                    viewHolder.mTvDesc.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
//                }
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Presenter.ViewHolder viewHolder, final Object item) {
        if (item instanceof Content.DataBean.WidgetsBean) {
            final ViewHolder vh = (ViewHolder) viewHolder;
            Log.e(TAG, "onBindViewHolder: " + ((Content.DataBean.WidgetsBean) item).getUrl());
            Glide.with(mContext)
                    .load(((Content.DataBean.WidgetsBean) item).getUrl())
                    .apply(new RequestOptions()
                            .transform(new CenterCrop(), new RoundedCorners(8))
                            .override(FontDisplayUtil.dip2px(mContext, 130),
                                    FontDisplayUtil.dip2px(mContext, 210))
                            .placeholder(R.drawable.bg_shape_default))
                    .into(vh.mIvPoster);
            String desc = ((Content.DataBean.WidgetsBean) item).getDesc();
            if (!TextUtils.isEmpty(desc)) {
                vh.mTvDesc.setText(desc);
            }

            ((ViewHolder) viewHolder).mClTypeSix.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Glide.with(mContext)
                            .load(((Content.DataBean.WidgetsBean) item).getUrl())
//                            .apply(RequestOptions.bitmapTransform(new BlurTransformation(context)))
                            .transform(new BlurTransformation(mContext ,20, 1))
//                            .centerCrop()
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    mContext.setBackground(resource);
                                }
                            });
                }
            });
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    public static class ViewHolder extends Presenter.ViewHolder {

        final TextView mTvDesc;
        final ImageView           mIvPoster;
        final ImgConstraintLayout mClTypeSix;

        public ViewHolder(View view) {
            super(view);
            mTvDesc = view.findViewById(R.id.tv_desc);
            mIvPoster = view.findViewById(R.id.iv_poster);
            mClTypeSix = view.findViewById(R.id.cl_type_six);
        }
    }

}
