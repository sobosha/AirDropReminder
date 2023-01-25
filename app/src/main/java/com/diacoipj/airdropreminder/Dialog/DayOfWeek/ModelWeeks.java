package com.diacoipj.airdropreminder.Dialog.DayOfWeek;

import android.widget.LinearLayout;
import android.widget.RadioButton;

public class ModelWeeks {
    private LinearLayout days ;
    private RadioButton rdbDays ;
    private boolean check = false ;

    public ModelWeeks(LinearLayout days, RadioButton rdbDays) {
        this.days = days;
        this.rdbDays = rdbDays;
    }

    public LinearLayout getDays() {
        return days;
    }

    public void setDays(LinearLayout days) {
        this.days = days;
    }

    public RadioButton getRdbDays() {
        return rdbDays;
    }

    public void setRdbDays(RadioButton rdbDays) {
        this.rdbDays = rdbDays;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
