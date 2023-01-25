package com.diacoipj.airdropreminder.Setting;

import android.widget.EditText;
import android.widget.NumberPicker;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewInitialize {


    public void setNpkHourMinValue(NumberPicker myNumberPicker, int max, IGetTime iGetTime) {
        String[] list = new String[max+1];
        myNumberPicker.setMinValue(1);
        myNumberPicker.setMaxValue(max+1);
        for (int i = 0; i < max+1; i++) {
            list[i] = (i < 10 ? ("0" +i) : String.valueOf(i));
        }
        myNumberPicker.setDisplayedValues(list);
        int hour = Integer.parseInt(getTimeCurrent().split(":")[0]);
        int min = Integer.parseInt(getTimeCurrent().split(":")[1]);
        if (max > 50) {
            myNumberPicker.setValue(min+1);
            iGetTime.Time(min);
        }
        else {
            myNumberPicker.setValue(hour+1);
            iGetTime.Time(hour);
        }

        myNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
            iGetTime.Time(i1-1);
        });
    }

    private String getTimeCurrent() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateToStr = format.format(today);
        String[] sp = Setting.textSeprator(dateToStr, ":");
        return sp[0] + ":" + sp[1];
    }


    public static boolean isTrueTextOfEditText (EditText editText) {
        return !editText.getText().toString().equals("") && editText.getText().toString().length() >= 2;
    }

}
