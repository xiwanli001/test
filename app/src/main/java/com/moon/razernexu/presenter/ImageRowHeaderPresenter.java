package com.moon.razernexu.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RestrictTo;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowHeaderPresenter;


import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP_PREFIX;

import com.moon.razernexu.R;

public class ImageRowHeaderPresenter extends RowHeaderPresenter {

    private final int mLayoutResourceId;
    private final boolean mAnimateSelect;
    private float mUnselectedAlpha = 0f ;

    public ImageRowHeaderPresenter() {
        this(R.layout.lb_img_row_header);
    }

    /**
     * @hide
     */
    @RestrictTo(LIBRARY_GROUP_PREFIX)
    public ImageRowHeaderPresenter(int layoutResourceId) {
        this(layoutResourceId, true);
    }

    /**
     * @hide
     */
    @RestrictTo(LIBRARY_GROUP_PREFIX)
    public ImageRowHeaderPresenter(int layoutResourceId, boolean animateSelect) {
        mLayoutResourceId = layoutResourceId;
        mAnimateSelect = animateSelect;
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);
        mUnselectedAlpha = parent.getResources()
                .getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);

        HeadViewHolder viewHolder =  new HeadViewHolder(root);
        if (mAnimateSelect) {
            setSelectLevel(viewHolder, 0f);
        }
        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        HeaderItem headerItem = item == null ? null : ((Row) item).getHeaderItem();
        if (headerItem == null) {
            if ( viewHolder.view.findViewById(R.id.row_header) != null) {
                ((TextView)viewHolder.view.findViewById(R.id.row_header)).setText(null);
            }
            viewHolder.view.setContentDescription(null);
            viewHolder.view.setVisibility(View.GONE);

        } else {
            if (viewHolder.view.findViewById(R.id.row_header) != null) {
                ((TextView)viewHolder.view.findViewById(R.id.row_header)).setText(headerItem.getName());
                ((TextView)viewHolder.view.findViewById(R.id.row_header)).setTextColor(viewHolder.view.getResources().getColor(R.color.colorWhite));
            }
            viewHolder.view.setContentDescription(headerItem.getContentDescription());
            viewHolder.view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSelectLevelChanged(ViewHolder holder) {
//        holder.view.setAlpha(mUnselectedAlpha + holder.getSelectLevel() * (1.0f - mUnselectedAlpha));
        Log.e("moon", "onSelectLevelChanged: " + holder );
        holder.view.setAlpha(1f);
    }

    public static class HeadViewHolder extends ViewHolder {

        public HeadViewHolder(View view) {
            super(view);

        }
    }
}
