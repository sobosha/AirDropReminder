package com.diacoipj.airdropreminder.Setting;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class CustomGridLayoutManager  extends GridLayoutManager {
    private boolean isScrollEnabled = false;

    public CustomGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
