package com.moon.razernexu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.leanback.widget.VerticalGridView;

import com.moon.razernexu.R;


public class TabVerticalGridView extends VerticalGridView {

    private static final String TAG = "TabVerticalGridView";

    private View mTabView;
    private Group mGroup;
    private Animation mShakeY;
    private boolean isPressUp = false;
    private boolean isPressDown = false;

    private int lastFocusPos = -1;
    private int numColumns = 1;

    public TabVerticalGridView(Context context) {
        this(context, null);
    }

    public TabVerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabVerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    public void setTabView(View tabView) {
        this.mTabView = tabView;
    }

    public void setGroup(Group mGroup) {
        this.mGroup = mGroup;
    }

    public boolean isPressUp() {
        return isPressUp;
    }

    public boolean isPressDown() {
        return isPressDown;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            isPressDown = false;
            isPressUp = false;
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    isPressDown = true;
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    isPressUp = true;
//                    if (getSelectedPosition() == 0) {
//                        mTabView.requestFocus();
//                        return true;
//                    }
                    break;
                case KeyEvent.KEYCODE_BACK:
                    backToTop();
                    return true;
                default:
                    break;
            }
        }
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        boolean handled = false;
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    handled = arrowScroll(FOCUS_DOWN);
                    break;
            }
        }
        return handled;
    }

    public boolean arrowScroll(int direction) {
        Log.e(TAG, "arrowScroll direction: "+direction );

        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            for (ViewParent parent = currentFocused.getParent(); parent instanceof ViewGroup;
                 parent = parent.getParent()) {
                if (parent == this) {
                    isChild = true;
                    break;
                }
            }
            if (!isChild) {
                // This would cause the focus search down below to fail in fun ways.
                final StringBuilder sb = new StringBuilder();
                sb.append(currentFocused.getClass().getSimpleName());
                for (ViewParent parent = currentFocused.getParent(); parent instanceof ViewGroup;
                     parent = parent.getParent()) {
                    sb.append(" => ").append(parent.getClass().getSimpleName());
                }
                currentFocused = null;
            }
        }

        boolean handled = false;

        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused,
                direction);
        if (nextFocused == null || nextFocused == currentFocused) {
            if (direction == FOCUS_DOWN) {
                if (currentFocused != null && getScrollState() == SCROLL_STATE_IDLE) {
                    if (mShakeY == null) {
                        mShakeY = AnimationUtils.loadAnimation(getContext(), R.anim.host_shake_y);
                    }
                    currentFocused.clearAnimation();
                    currentFocused.startAnimation(mShakeY);

                }
                handled = true;
            }
        }
        return handled;
    }

    public void backToTop() {
        if (mTabView != null) {
            if (mGroup != null && mGroup.getVisibility() != View.VISIBLE) {
                mGroup.setVisibility(View.VISIBLE);
            }
            mTabView.requestFocus();
        }
        scrollToPosition(0);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        Log.e(TAG, "arrowScroll direction: "+dy );
    }

    @Override
    public View focusSearch(View focused, int direction) {
        View nextFocusView = FocusFinder.getInstance().findNextFocus(this, focused, direction);
        if (nextFocusView == null && lastFocusPos != -1 && lastFocusPos < getAdapter().getItemCount() - 1) {
            switch (direction) {
                case View.FOCUS_DOWN:
                case View.FOCUS_RIGHT:
                    int nextPos = (getChildCount() / numColumns) * numColumns;
                    Log.d(TAG, "focusSearch: getChildCount" + getChildCount() + "nextPos" + nextPos);
                    return getChildAt(nextPos);
            }
        }
        return super.focusSearch(focused, direction);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        boolean canScrollHorizontally = super.canScrollHorizontally(direction);
        Log.d(TAG, "canScrollHorizontally: " + canScrollHorizontally);
        return canScrollHorizontally;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        boolean canScrollVertically = super.canScrollVertically(direction);
        Log.d(TAG, "canScrollHorizontally: " + canScrollVertically);
        return canScrollVertically;
    }


}
