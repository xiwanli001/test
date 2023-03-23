package com.moon.razernexu.utils;

import android.view.View;


public class FocusUtisl {

    public static class MyFocuchange implements View.OnFocusChangeListener {

        View rl_scale = null;
        View tv_name = null;

        /**
         * 图片,文字都放缩
         *
         * @param rl_scale
         */
        public MyFocuchange(View rl_scale, View tv_name) {
            this.rl_scale = rl_scale;
            this.tv_name = tv_name;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (rl_scale != null) {
                    rl_scale.animate().scaleX(1.5f).scaleY(1.5f).start();

                }
                if (tv_name != null) {
                    tv_name.animate().scaleX(1.3f).scaleY(1.3f).start();
                    tv_name.animate().alpha(1.0f).start();
                }
            } else {
                if (rl_scale != null) {
                    rl_scale.animate().scaleX(1.f).scaleY(1.f).start();
                }
                if (tv_name != null) {
                    tv_name.animate().scaleX(1f).scaleY(1f).start();
                    tv_name.animate().alpha(0.0f).start();
                }

            }

        }
    }

}