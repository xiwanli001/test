package com.moon.razernexu.ui;

import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowPresenter;


public class BaseListRowPresenter extends ListRowPresenter {

    @Override
    protected void onRowViewAttachedToWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewAttachedToWindow(vh);
        if (getOnItemViewClickedListener() != null) {
            vh.setOnItemViewClickedListener(getOnItemViewClickedListener());
        }

        if (getOnItemViewSelectedListener() != null) {
            vh.setOnItemViewSelectedListener(getOnItemViewSelectedListener());
        }
    }

    @Override
    protected void onRowViewDetachedFromWindow(RowPresenter.ViewHolder vh) {
        super.onRowViewDetachedFromWindow(vh);
        if (getOnItemViewClickedListener() != null) {
            vh.setOnItemViewClickedListener(null);
        }
        if (getOnItemViewSelectedListener() != null) {
            vh.setOnItemViewSelectedListener(null);
        }
    }

    private BaseOnItemViewClickedListener onItemViewClickedListener;

    public void setOnItemViewClickedListener(BaseOnItemViewClickedListener onItemViewClickedListener) {
        this.onItemViewClickedListener = onItemViewClickedListener;
    }

    public BaseOnItemViewClickedListener getOnItemViewClickedListener() {
        return onItemViewClickedListener;
    }
    private BaseOnItemViewSelectedListener onItemViewSelectedListener;

    public void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener onItemViewSelectedListener) {
        this.onItemViewSelectedListener = onItemViewSelectedListener;
    }

    public BaseOnItemViewSelectedListener getOnItemViewSelectedListener() {
        return onItemViewSelectedListener;
    }

}
