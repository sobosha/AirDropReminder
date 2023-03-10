package com.diacoipj.airdropreminder.Setting.ViewPagerAnimation;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import static java.lang.Math.min;

public class BackgroundToForegroundPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float pos ) {
        final float height = page.getHeight();
        final float width = page.getWidth();
        final float scale = min( pos < 0 ? 1f : Math.abs(1f - pos), 1f );

        page.setScaleX( scale );
        page.setScaleY( scale );
        page.setPivotX( width * 0.5f );
        page.setPivotY( height * 0.5f );
        page.setTranslationX( pos < 0 ? width * pos : -width * pos * 0.25f );
    }
}