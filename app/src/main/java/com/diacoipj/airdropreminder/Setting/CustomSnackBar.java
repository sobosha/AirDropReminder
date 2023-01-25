package com.diacoipj.airdropreminder.Setting;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.R;


public class CustomSnackBar extends RelativeLayout {
    String type, desc;

    public CustomSnackBar(Context context, String type, String desc) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rel_custom_snack_bar, this, true);
        this.type = type;
        this.desc = desc;
        onStart();
        new Setting().setTypeFace(findViewById(R.id.txtDesc));
    }

    private void onStart() {
        switch (type) {
            case "accept":
                setColors(desc,  "#17bd79",
                        R.drawable.happy,  R.drawable.shape_best,
                        R.color.colorLightGreen,true);
                break;
            case "pending":
                setColors(desc,  "#0671f2",
                        R.drawable.emoji_relx,  R.drawable.shape_survay,
                         R.color.newBlue,true);
                break;
            case "reject":
                setColors(desc,  "#f60057",
                        R.drawable.sad,  R.drawable.shape_disapproval,
                        R.color.newRed,true);
                break;
            case "warning":
                setColors(desc,"#ffb726",
                        R.drawable.ic_report, R.drawable.shape_warning, R.color.newyellow,true);
                break;
            case "normal":
                setColors(desc,  "#ffffff",
                        R.drawable.zang, R.drawable.shape_normal,R.color.newyellow,false);
                break;
        }
        findViewById(R.id.linBackTop).setVisibility(GONE);
    }

       private void setColors(String desc, String lineColor, int imgImojee, int icon, int changeColor,boolean needChangeColor) {
        ((TextView) findViewById(R.id.txtDesc)).setText(desc);
        ((TextView) findViewById(R.id.txtDesc)).setTextColor(Color.parseColor(lineColor));
        ((ImageView) findViewById(R.id.imojii)).setImageResource(imgImojee);
        findViewById(R.id.line).setBackgroundColor(Color.parseColor(lineColor));
        findViewById(R.id.linBack).setBackgroundResource(icon);
        if (needChangeColor)
        new Setting().changeSvgColor(getContext(), findViewById(R.id.imojii), changeColor);
    }
}
