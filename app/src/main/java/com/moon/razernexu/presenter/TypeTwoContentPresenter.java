package com.moon.razernexu.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.moon.razernexu.R;
import com.moon.razernexu.bean.Content;
import com.moon.razernexu.utils.FocusUtisl;
import com.moon.razernexu.utils.FontDisplayUtil;

public class TypeTwoContentPresenter extends Presenter {
    private Context mContext;

    private static final String TAG = "TypeTwoContentPresenter";

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_two_layout, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnFocusChangeListener(new FocusUtisl.MyFocuchange(viewHolder.mIvTypeTwoPoster,null ));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof Content.DataBean.WidgetsBean) {
            ViewHolder vh = (ViewHolder) viewHolder;
            Glide.with(mContext)
                    .load(((Content.DataBean.WidgetsBean) item).getUrl())
                    .apply(new RequestOptions()
                            .transform(new CenterCrop(), new RoundedCorners(10))
                            .override(FontDisplayUtil.dip2px(mContext, 282),
                                    FontDisplayUtil.dip2px(mContext, 188))
                            .placeholder(R.drawable.bg_shape_default))
                    .into(vh.mIvTypeTwoPoster);
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    public static class ViewHolder extends Presenter.ViewHolder {

        private final ImageView mIvTypeTwoPoster;

        public ViewHolder(View view) {
            super(view);
            mIvTypeTwoPoster = view.findViewById(R.id.iv_type_two_poster);
        }
    }
}

