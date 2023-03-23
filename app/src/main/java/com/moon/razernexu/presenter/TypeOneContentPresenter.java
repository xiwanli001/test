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


public class TypeOneContentPresenter extends Presenter {
    private Context mContext;

    private static final String TAG = "TypeOneContentPresenter";

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_one_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnFocusChangeListener(new FocusUtisl.MyFocuchange(null , viewHolder.mIvTypeOneName));
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
                            .override(FontDisplayUtil.dip2px(mContext, 198),
                                    FontDisplayUtil.dip2px(mContext, 111))
                            .placeholder(R.drawable.bg_shape_default))
                    .into(vh.mIvTypeOnePoster);

            if (!TextUtils.isEmpty(((Content.DataBean.WidgetsBean) item).getName())) {
                vh.mIvTypeOneName.setText(((Content.DataBean.WidgetsBean) item).getName());
            }
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    public static class ViewHolder extends Presenter.ViewHolder {

        final ImageView mIvTypeOnePoster;
        final TextView  mIvTypeOneName;

        public ViewHolder(View view) {
            super(view);
            mIvTypeOnePoster = view.findViewById(R.id.iv_type_one_poster);
            mIvTypeOneName = view.findViewById(R.id.tv_type_one_name);
        }
    }
}
