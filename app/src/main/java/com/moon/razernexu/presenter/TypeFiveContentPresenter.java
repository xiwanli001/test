package com.moon.razernexu.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.moon.razernexu.R;
import com.moon.razernexu.bean.Content;
import com.moon.razernexu.utils.FocusUtisl;
import com.moon.razernexu.utils.FontDisplayUtil;


public class TypeFiveContentPresenter extends Presenter {
    private Context mContext;

    private static final String TAG = "TypeFiveContentPresenter";

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_five_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnFocusChangeListener(new FocusUtisl.MyFocuchange(viewHolder.mIvTypeFivePoster , null));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof Content.DataBean.WidgetsBean) {
            ViewHolder vh = (ViewHolder) viewHolder;
            Glide.with(mContext)
                    .load(((Content.DataBean.WidgetsBean) item).getUrl())
                    .apply(new RequestOptions()
                            .transform(new CenterCrop(), new RoundedCorners(8))
                            .override(FontDisplayUtil.dip2px(mContext, 280),
                                    FontDisplayUtil.dip2px(mContext, 126))
                            .placeholder(R.drawable.bg_shape_default))
                    .into(vh.mIvTypeFivePoster);
            if (!TextUtils.isEmpty(((Content.DataBean.WidgetsBean) item).getName())) {
                vh.mIvTypeFiveName.setText(((Content.DataBean.WidgetsBean) item).getName());
            }
            if(!TextUtils.isEmpty(((Content.DataBean.WidgetsBean) item).getDesc())){
                vh.mIvTypeFiveDesc.setText(((Content.DataBean.WidgetsBean) item).getDesc());
            }
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    public static class ViewHolder extends Presenter.ViewHolder {

        final ImageView mIvTypeFivePoster;
        final TextView mIvTypeFiveName;
        final TextView mIvTypeFiveDesc;

        public ViewHolder(View view) {
            super(view);
            mIvTypeFivePoster = view.findViewById(R.id.iv_type_five_poster);
            mIvTypeFiveName = view.findViewById(R.id.tv_type_five_name);
            mIvTypeFiveDesc = view.findViewById(R.id.tv_type_five_desc);
        }
    }
}
