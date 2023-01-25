package com.diacoipj.airdropreminder.Other.Reminders.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class NotifManager {

    Context context ;
    public NotifManager (Context context) {
        this.context = context ;
    }

    AlarmManager alarmManager ;
    PendingIntent notifPIntent ;
    int [] Miladi ;
    int hourNotif , minNotif ;
    String sharedPrefKey ;
    public NotifManager setNotificationReminder (int [] Miladi , int hourNotif , int minNotif , int RequestCode , String sharedPrefKey) {
        this.Miladi = Miladi ;
        this.hourNotif = hourNotif ;
        this.minNotif = minNotif ;
        this.sharedPrefKey = sharedPrefKey ;
        setNotif(RequestCode , false);
        return this;
    }


    public void setNotif (int RequestCode , boolean cancel) {
        Calendar instance = Calendar.getInstance();
        if (!cancel) {
            instance.set(Calendar.YEAR, Miladi[0]);
            instance.set(Calendar.MONTH, Miladi[1] - 1);
            instance.set(Calendar.DAY_OF_MONTH, Miladi[2]);
            instance.set(Calendar.HOUR_OF_DAY, hourNotif);
            instance.set(Calendar.MINUTE, minNotif);
            instance.set(Calendar.SECOND, 30);
        }
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context , NotifReceiver.class);
            intent.putExtra("KEYSHARED" , sharedPrefKey);
            intent.setAction("KEYSHARED");

            notifPIntent = PendingIntent.getBroadcast(context, RequestCode, intent, 0);
            if (Build.VERSION.SDK_INT >= 23 && !cancel) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), notifPIntent);
            } else if (Build.VERSION.SDK_INT >= 19 && !cancel) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), notifPIntent);
            } else if (!cancel){
                alarmManager.set(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(), notifPIntent);
            }
            if (cancel) {
                if (notifPIntent!=null) {
                    alarmManager.cancel(notifPIntent);
                }
            }
    }
    long timeInsta ;
    public void setNotifAfterWeek (int RequestCode) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(context, NotifReceiver.class);
        intent.putExtra("Action" , 404);
        intent.setAction("Instagram");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCode, intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        timeInsta = calendar.getTimeInMillis() < System.currentTimeMillis() ? am.INTERVAL_DAY+calendar.getTimeInMillis() : calendar.getTimeInMillis();
        am.setRepeating(am.RTC_WAKEUP, timeInsta , am.INTERVAL_DAY*7, pendingIntent);
    }

    long timeEvent ;
    public void setNotifWater (int RequestCode) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 40);
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(context, NotifReceiver.class);
        intent.putExtra("Water" , 404404);
        intent.setAction("Water");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCode, intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        timeEvent = calendar.getTimeInMillis() < System.currentTimeMillis() ? am.INTERVAL_DAY+calendar.getTimeInMillis() : calendar.getTimeInMillis();
        am.setRepeating(am.RTC_WAKEUP,timeEvent, am.INTERVAL_DAY, pendingIntent);
    }

    long timeProfile ;
    public void setProfileNotif (int RequestCode) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 40);
        calendar.set(Calendar.SECOND, 0);
        Intent intent = new Intent(context, NotifReceiver.class);
        intent.putExtra("Prof" , 365);
        intent.setAction("Prof");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCode, intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        timeProfile = calendar.getTimeInMillis() < System.currentTimeMillis() ? am.INTERVAL_DAY+calendar.getTimeInMillis() : calendar.getTimeInMillis();
        if (Build.VERSION.SDK_INT >= 23) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeProfile, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            am.setExact(AlarmManager.RTC_WAKEUP, timeProfile, pendingIntent);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, timeProfile, pendingIntent);
        }
    }
}
