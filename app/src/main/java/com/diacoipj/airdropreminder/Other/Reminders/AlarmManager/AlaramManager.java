package com.diacoipj.airdropreminder.Other.Reminders.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import com.diacoipj.airdropreminder.Setting.DateManager;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mSoundManager;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlaramManager {

    Context context ;
    int hour ,  min ;
    boolean isNotif = false ;
    String title="",link="";
    String textNotif="";
    public  AlaramManager(Context context , int hour , int min  , int ID , boolean isNotif ,String desc,String title,String link ){
        this.context = context;
        this.isNotif = isNotif;
        if(hour !=-1 && min!=-1) {
            this.requestCode = ID;
            this.hour = hour;
            this.min = min;
            textNotif=desc;
            this.title=title;
            this.link=link;
            setSchedual();
        }
    }

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar instance ;
    int requestCode ;
    public void setSchedual () {
        if (Calendar.getInstance().get(11) < hour) {
            this.date = DateManager.Today() ;
        } else if (Setting.currentHour() != hour) {
            this.date = DateManager.Tommorow(DateManager.Today(), 1) ;
        } else if (Setting.currentMin() < min) {
            this.date = DateManager.Today() ;
        } else {
            this.date =  DateManager.Tommorow(DateManager.Today(), 1) ;
        }
        startAlarm(context) ;

    }

    Date date ;
    public  void startAlarm(Context context ){
        instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, hour);
        instance.set(12, min);
        instance.set(13, 1);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent ;
        if(!isNotif) {
             intent = new Intent(context, AlertReceiver.class);
        } else {
             intent = new Intent(context, NotifReceiver.class);

        }
        intent.setAction("liomAlarm");
        intent.putExtra("NotifReminder", instance.getTimeInMillis());
        intent.putExtra("description",textNotif);
        intent.putExtra("Time",hour+":"+min);
        intent.putExtra("title",title);
        intent.putExtra("link",link);
        pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), pendingIntent);
        }
    }

    public  void cancle(){
        if(pendingIntent!=null) {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    List<ReminderData> reminderData = new ArrayList<>();
    public AlaramManager afterAlarm(Context context){
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateToStr = format.format(today);
        String[] sp = Setting.textSeprator(dateToStr,":");
        String substr = sp[0]+sp[1];
//        int a = Integer.parseInt(sp[0])+12;
//        String substr2 = a +sp[1];

        /*new DataBaseAccess().setReminders(context, reminderData , -1);//all
        for(int i  = 0 ; i<reminderData.size() ; i++){
            String[] time = Setting.textSeprator(reminderData.get(i).getAlertHour(),":");
            String t = (Integer.parseInt(time[0])<10? "0"+time[0] : time[0]) + (Integer.parseInt(time[1])<10 ? "0" + time[1] : time[1]);
            if(reminderData.get(i).getType()==2 && Integer.parseInt(t) == Integer.parseInt(substr) ){
                if(reminderData.get(i).getSound()==2){
                    setNotifSound(reminderData.get(i).getTag());
                }
                reminderData.get(i).setFlag(3);
                requestCode  = reminderData.get(i).getId();
                hour = Integer.parseInt(sp[0]) ;
                min =  Integer.parseInt(sp[1]) ;
                isNotif = isNotifEnable(context) ;
                setSchedual() ;
            }


            if(reminderData.get(i).getType()==1 && Integer.parseInt(t) == Integer.parseInt(substr)){
                if(reminderData.get(i).getSound()==2){
                    setNotifSound(reminderData.get(i).getTag());
                }
                reminderData.get(i).setFlag(3);
                requestCode  = reminderData.get(i).getId();
                hour = Integer.parseInt(sp[0]) ;
                min =  Integer.parseInt(sp[1]) ;
                isNotif = false ;
                setSchedual() ;
             }
        }*/
        return  this;
    }



    int [] Miladi ;
    int hourNotif , minNotif ;
    public AlaramManager setNotificationReminder (int [] Miladi , int hourNotif , int minNotif , int RequestCode) {
        this.Miladi = Miladi ;
        this.hourNotif = hourNotif ;
        this.minNotif = minNotif ;
        setNotif(RequestCode);
        return this;
    }


    public void setNotif ( int RequestCode) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR         , Miladi[0]);
        instance.set(Calendar.MONTH        , Miladi[1]-1);
        instance.set(Calendar.DAY_OF_MONTH , Miladi[2]);
        instance.set(Calendar.HOUR_OF_DAY  , hourNotif);
        instance.set(Calendar.MINUTE       , minNotif);
        instance.set(Calendar.SECOND       , 30);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context , NotifReceiver.class);
        intent.putExtra("NotifReminder" , "");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context , RequestCode , intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), pendingIntent);
        }
    }
    public void setNotifSound(String type)
    {
        switch (type)
        {
            case "other":
                mSoundManager.getGlobal().DingAlarm();
                break;
            case "sport":
                mSoundManager.getGlobal().sportAlarm();
                break;
            case "fruit":
                mSoundManager.getGlobal().fruitAlarm();
                break;
            case "sleep":
                mSoundManager.getGlobal().sleepAlarm();
                break;
            case "water":
                mSoundManager.getGlobal().waterAlarm();
                break;
            case "book":
                mSoundManager.getGlobal().bookAlarm();
                break;
            case "mobile":
                mSoundManager.getGlobal().mobileAlarm();
                break;
            case "drug":
                mSoundManager.getGlobal().drugAlarm();
                break;
        }
    }

    public boolean isNotifEnable(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(context))
                return true;
            else
                return false ;
        } else
            return false ;
    }

}
