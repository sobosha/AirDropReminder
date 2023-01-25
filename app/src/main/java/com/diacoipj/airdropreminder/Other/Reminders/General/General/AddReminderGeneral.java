package com.diacoipj.airdropreminder.Other.Reminders.General.General;

import android.content.Context;
import android.graphics.PorterDuff;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomRel;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.ViewInitialize;

public class AddReminderGeneral extends CustomRel {
    NumberPicker npkHour , npkMinute;
    ViewInitialize viewInitialize = new ViewInitialize() ;
    HourMinCallBack hourMinCallBack ;
    public AddReminderGeneral(Context context , HourMinCallBack hourMinCallBack) {
        super(context, R.layout.add_reminder_general);
        this.hourMinCallBack = hourMinCallBack ;
        initializeStyle();
        OnClick();
        initializeNumberPicker();
    }

    int min , hour ;
    private void initializeStyle () {
        npkHour   = findViewById(R.id.npkHour);
        npkMinute = findViewById(R.id.npkMinute);
        ((ProgressBar)findViewById(R.id.prgCircleRemember)).getProgressDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorLightOrange), PorterDuff.Mode.SRC_IN);
        new Setting().setTypeFace(findViewById(R.id.txtAcceptRemember));
    }

    private void OnClick () {
        findViewById(R.id.txtAcceptRemember).setOnClickListener(view -> hourMinCallBack.onSetTime(hour , min));
    }

    private void initializeNumberPicker () {
        viewInitialize.setNpkHourMinValue(npkHour, 23, time -> hour = time);
        viewInitialize.setNpkHourMinValue(npkMinute, 59 , time -> min = time);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.getGlobal().HideMyRelDialog();
    }
}
