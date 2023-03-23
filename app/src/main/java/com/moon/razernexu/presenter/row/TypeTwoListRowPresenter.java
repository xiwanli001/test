package com.moon.razernexu.presenter.row;

import static androidx.leanback.widget.BaseGridView.WINDOW_ALIGN_NO_EDGE;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowHeaderPresenter;
import androidx.leanback.widget.RowPresenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.moon.razernexu.MainActivity;
import com.moon.razernexu.R;
import com.moon.razernexu.bean.Content;
import com.moon.razernexu.ui.BaseListRowPresenter;
import com.moon.razernexu.utils.BlurTransformation;
import com.moon.razernexu.utils.FontDisplayUtil;

public class TypeTwoListRowPresenter extends BaseListRowPresenter {
    private MainActivity context ;
    @SuppressLint("RestrictedApi")
    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);
        final ViewHolder rowViewHolder = (ViewHolder) holder;

        rowViewHolder.getGridView().setHorizontalSpacing(FontDisplayUtil.dip2px(rowViewHolder.getGridView().getContext(), 24));
//        rowViewHolder.getGridView().setFocusScrollStrategy(HorizontalGridView.FOCUS_SCROLL_ITEM);
        rowViewHolder.getGridView().setWindowAlignment(WINDOW_ALIGN_NO_EDGE);
        rowViewHolder.getGridView().setWindowAlignmentOffsetPercent(18);
        RowHeaderPresenter.ViewHolder vh = rowViewHolder.getHeaderViewHolder();
        TextView textView = vh.view.findViewById(R.id.row_header);
        textView.setTextSize(FontDisplayUtil.dip2px(textView.getContext(), 15));
        textView.setTextColor(Color.parseColor("#AFFFFFFF"));                        //Color.BLUE
        textView.setPadding(0, 0, 0, 20);
        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder,
                                      Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                if (item instanceof Content.DataBean.WidgetsBean) {
                    Toast.makeText(((ViewHolder) rowViewHolder).getGridView().getContext(),
                            "位置:" + ((ViewHolder) rowViewHolder).getGridView().getSelectedPosition(),
                            Toast.LENGTH_SHORT).show();


                    context = (MainActivity)((ViewHolder) rowViewHolder).getGridView().getContext();
                    Glide.with(context)
                            .load(((Content.DataBean.WidgetsBean) item).getUrl())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
//                            .apply(RequestOptions.bitmapTransform(new BlurTransformation(context)))
                            .error(R.drawable.bg_gradient_home)
                            .transform(new BlurTransformation(context ,10, 1))
//                            .centerCrop()
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    context.setBackground(resource);
                                }
                            });

                }

            }
        });


        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() {
            @Override
            public void onItemSelected(Presenter.ViewHolder itemViewHolder,
                                       Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                if (item instanceof Content.DataBean.WidgetsBean) {


                    context = (MainActivity)((ViewHolder) rowViewHolder).getGridView().getContext();
                    Glide.with(context)
                            .load(((Content.DataBean.WidgetsBean) item).getUrl())
                            .centerCrop()
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    context.setBackground(resource);
                                }
                            });

                }

            }
        });

    }
}
