package com.moon.razernexu.presenter.row;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowHeaderPresenter;
import androidx.leanback.widget.RowPresenter;

import com.moon.razernexu.R;
import com.moon.razernexu.bean.Content;
import com.moon.razernexu.ui.BaseListRowPresenter;
import com.moon.razernexu.utils.FontDisplayUtil;

public class ContentListRowPresenter extends BaseListRowPresenter {

    private static final String TAG = "ContentListRowPresenter";

    @SuppressLint("RestrictedApi")
    @Override
    protected void initializeRowViewHolder(final RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);

        final ViewHolder rowViewHolder = (ViewHolder) holder;
        rowViewHolder.getGridView().setHorizontalSpacing(
                FontDisplayUtil.dip2px(rowViewHolder.getGridView().getContext(), 24));
        RowHeaderPresenter.ViewHolder headerViewHolder = holder.getHeaderViewHolder();
        final TextView tv = headerViewHolder.view.findViewById(R.id.row_header);
//        tv.setTextColor(tv.getContext().getResources().getColor(R.color.colorWhite));
//        tv.setPadding(0, 20, 0, 20);
//        tv.setTextSize(FontDisplayUtil.dip2px(rowViewHolder.getGridView().getContext(), 12));
        rowViewHolder.getGridView().setFocusScrollStrategy(HorizontalGridView.FOCUS_SCROLL_ITEM);

        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder,
                                      Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                if (item instanceof Content.DataBean.WidgetsBean) {
//                    tv.getContext().startActivity(new Intent(tv.getContext(), VideoDetailActivity.class));

                }

            }
        });

        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() {
            @Override
            public void onItemSelected(Presenter.ViewHolder itemViewHolder,
                                       Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                if (item instanceof Content.DataBean.WidgetsBean) {
                    RowHeaderPresenter.ViewHolder headerViewHolder = holder.getHeaderViewHolder();
                    final TextView tv = headerViewHolder.view.findViewById(R.id.row_header);
                    int selected_background = tv.getResources().getColor(R.color.colorBlue);
                    int default_background = tv.getResources().getColor(R.color.colorWhite);
//                    int color = selected ? selected_background : default_background;
                    tv.setTextColor(tv.getContext().getResources().getColor(R.color.colorBlue));
                }

            }
        });
    }
}
