package com.moon.razernexu.presenter.row;

import static androidx.leanback.widget.BaseGridView.WINDOW_ALIGN_BOTH_EDGE;
import static androidx.leanback.widget.BaseGridView.WINDOW_ALIGN_HIGH_EDGE;
import static androidx.leanback.widget.BaseGridView.WINDOW_ALIGN_LOW_EDGE;
import static androidx.leanback.widget.BaseGridView.WINDOW_ALIGN_NO_EDGE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;

import com.moon.razernexu.bean.Content;
import com.moon.razernexu.ui.BaseListRowPresenter;
import com.moon.razernexu.utils.FontDisplayUtil;

public class TypeFiveListRowPresenter extends BaseListRowPresenter {

    @SuppressLint("RestrictedApi")
    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);
        final ViewHolder rowViewHolder = (ViewHolder) holder;
        rowViewHolder.getGridView().setHorizontalSpacing(FontDisplayUtil.dip2px(rowViewHolder.getGridView().getContext(), 24));
//        rowViewHolder.getGridView().setFocusScrollStrategy(HorizontalGridView.FOCUS_SCROLL_ALIGNED);
        rowViewHolder.getGridView().setWindowAlignment(WINDOW_ALIGN_NO_EDGE);
        rowViewHolder.getGridView().setWindowAlignmentOffsetPercent(18);

        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder,
                                      Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                if (item instanceof Content.DataBean.WidgetsBean) {
                    Toast.makeText(((ViewHolder) rowViewHolder).getGridView().getContext(),
                            "位置:" + ((ViewHolder) rowViewHolder).getGridView().getSelectedPosition(),
                            Toast.LENGTH_SHORT).show();
//                    ((ViewHolder) rowViewHolder).getGridView().getContext().startActivity(new Intent(((ViewHolder) rowViewHolder).getGridView().getContext(), VideoDetailActivity.class));

                }

            }
        });
        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() {
            @Override
            public void onItemSelected(Presenter.ViewHolder itemViewHolder,
                                       Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                Log.e("moon", "ooooooo----setOnItemViewSelectedListener: " +itemViewHolder+"row" );
                if (item instanceof Content.DataBean.WidgetsBean) {
                    Log.e("moon", "setOnItemViewSelectedListener: " +itemViewHolder+"row" );
//                    ((ViewHolder) rowViewHolder).getGridView().getContext().;

                }

            }
        });


    }

    @Override
    protected void onRowViewSelected(RowPresenter.ViewHolder holder, boolean selected) {
        super.onRowViewSelected(holder, selected);
        Log.e("moon", " moon-row-onRowViewSelected: " + selected);
    }

    @Override
    protected boolean isClippingChildren() {
        Log.e("moon", " moon-row-isClippingChildren: " + "");
        return super.isClippingChildren();

    }

}
