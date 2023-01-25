package com.diacoipj.airdropreminder.Setting;


import android.content.Context;

import com.diacoipj.airdropreminder.MainActivity;

import com.diacoipj.airdropreminder.MyApp;

import java.util.ArrayList;
import java.util.List;

public class nValue {

    private static nValue global;

    public static void setGlobal(nValue global) {
        nValue.global = global;
    }

    public static nValue getGlobal()
    {
        if(global != null)
            return global;
        else
            return new nValue();
    }
    private nValue()
    {
        global = this;
    }

    public static String marketModel = "bazar";
    /* myket   bazar   iranapps   */
    public static String getValidateMarket()
    {
        return marketModel;
    }


    public String StartjoinModel;

    private String joinModel;
    public String getJoinModel() {
        return joinModel;
    }

    public void setJoinModel(String joinModel) {
        this.joinModel = joinModel;
    }

    /*-------------------------------------------------------------------------------------------------------*/
    String RText  , RDate;

    public String getRText() {
        return RText;
    }

    public void setRText(String RText) {
        this.RText = RText;
    }

    public String getRDate() {
        return RDate;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }
    public Context getContext() {

        return MyApp.getAppContext()!=null ? MyApp.getAppContext() : MainActivity.getGlobal();
    }

    String currentDate ;

    public String getCurrentDate() {
        return currentDate!=null?currentDate:new DateManager().getTodayDateForNotif(true);
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
    /*---------------------- reminder flags ---------------------------------*/
    public List<String> dayReminders ;

    public List<String> getDayReminder() {
        return dayReminders ==null ? new ArrayList<>() : dayReminders ;
    }

    public void setDaysReminder(List<String> dayReminders) {
        this.dayReminders = dayReminders;
    }

    String time ;
    public void setTime (String time) {
        this.time = time ;
    }

    public String getTime () {
        return time;
    }

    boolean isLaunched;

    public boolean isLaunched() {
        return isLaunched;
    }

    public void setLaunched(boolean launched) {
        isLaunched = launched;
    }


//    ---------------------------- set moshavere visibility -------------------------

    boolean hasUserData;


    boolean blockBack;

    public boolean isBlockBack() {
        return blockBack;
    }

    public void setBlockBack(boolean blockBack) {
        this.blockBack = blockBack;
    }


}

