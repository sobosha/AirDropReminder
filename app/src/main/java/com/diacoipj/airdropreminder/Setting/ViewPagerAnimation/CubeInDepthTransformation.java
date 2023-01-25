package com.diacoipj.airdropreminder.Setting.ViewPagerAnimation;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class CubeInDepthTransformation implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        page.setCameraDistance(20000);


        if (position < -1){
            page.setAlpha(0);
        }
        else if (position <= 0){
            page.setAlpha(1);
            page.setPivotX(page.getWidth());
            page.setRotationY(90*Math.abs(position));
        }
        else if (position <= 1){
            page.setAlpha(1);
            page.setPivotX(0);
            page.setRotationY(-90*Math.abs(position));
        }
        else{
            page.setAlpha(0);
        }


        final float max = Math.max(.4f, 1 - Math.abs(position));
        if (Math.abs(position) <= 0.5){
            page.setScaleY(max);
        }
        else if (Math.abs(position) <= 1){
            page.setScaleY(max);

        }


    }
}