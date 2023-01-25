package com.diacoipj.airdropreminder.Other.Reminders.AlarmManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.diacoipj.airdropreminder.BroadTest;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mSoundManager;
import com.diacoipj.airdropreminder._Core.DB.DataBaseAccess;
import com.diacoipj.airdropreminder._Core.DB.mDataBase;
import com.diacoipj.airdropreminder._Core.respons.NotifModel;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotifReceiver extends BroadcastReceiver {
    NotificationManager notifManager;
    String offerChannelId = "Offers";
    PendingIntent mpIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intentNext = new Intent(context, BroadTest.class)
                .setAction(intent.getAction());
        mpIntent = PendingIntent.getBroadcast(context, 0,
                intentNext, PendingIntent.FLAG_UPDATE_CURRENT);

        createNotifChannel(context);
        afterNotif(context, intent);
    }


    private void createNotifChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "Shop offers";
            String offerChannelDescription = "Best offers for customers";
            int offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notifChannel = new NotificationChannel(offerChannelId, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            //notifChannel.enableVibration(true);
            notifChannel.enableLights(true);
            notifChannel.setLightColor(Color.GREEN);

            notifManager.createNotificationChannel(notifChannel);

        }

    }

    public void pendingNotification(Context context, Intent intent) {
        //new DataBaseAccess().setEvents(context , event , dateId , null);


            String text=intent.getStringExtra("link");
            String title=intent.getStringExtra("title");
            NotificationCompat.Builder pNotifBuilder = new NotificationCompat.Builder(context, offerChannelId)
                    .setSmallIcon(R.drawable.zangoole)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.app_icon))
                    .setContentIntent(mpIntent)
                    .setAutoCancel(true);

            notifManager.notify(intent.hasExtra("Action") ? intent.getIntExtra("Action", 0) : 2, pNotifBuilder.build());
    }


    List<ReminderData> reminderData = new ArrayList<>();
    boolean isFind;
    ReminderData data;
    int savePosNotifKEY = -1;
    List<NotifModel> notifModels = new ArrayList<>();
    boolean isNotifDays = false ;
    Calendar calendar = Calendar.getInstance() ;
    public void afterNotif(Context context, Intent intent) {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateToStr = format.format(today);
        String[] sp = Setting.textSeprator(dateToStr, ":");
        String substr = sp[0] + sp[1];

        if (intent.hasExtra("NotifReminder")) {
                new DataBaseAccess().setReminders(context, reminderData, -1);//all
                for (int i = 0; i < reminderData.size(); i++) {
                    String[] time = Setting.textSeprator(reminderData.get(i).getAlertHour(), ":");
                    String t = (Integer.parseInt(time[0])<10? "0"+time[0] : time[0]) + (Integer.parseInt(time[1])<10 ? "0" + time[1] : time[1]);
                    if (reminderData.get(i).getType() == 2 && Integer.parseInt(t) == Integer.parseInt(substr)) {

                        data = reminderData.get(i);
                        for (int j = 0; j < reminderData.get(i).getDaysReminderList().size() ; j++) {
                            if (calendar.get(Calendar.DAY_OF_WEEK) == Integer.parseInt(reminderData.get(i).getDaysReminderList().get(j))) {
                                isNotifDays = true ;
                            }
                        }
                        if (isNotifDays || reminderData.get(i).getDaysReminderList().isEmpty()) {
                            pendingNotification(context, intent);
                            isNotifDays = false ;
                        }
                        if (!reminderData.get(i).getDaysReminderList().isEmpty()) {
                            reminderData.get(i).setFlag(0);
                            new AlaramManager(context, -1 , -1 ,-1, true,intent.getStringExtra("description"),intent.getStringExtra("title"),intent.getStringExtra("link")).afterAlarm(context);
                        } else {
                            reminderData.get(i).setFlag(3);
                        }
                        onUpdateDB(reminderData.get(i), context);
                        isFind = true;
                    }

                    if (reminderData.get(i).getType() == 1 && Integer.parseInt(t) == Integer.parseInt(substr)) {
                        reminderData.get(i).setFlag(3);
                        data = reminderData.get(i);
                        onUpdateDB(reminderData.get(i), context);
                        pendingNotification(context, intent);
                        new AlaramManager(context, Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), reminderData.get(i).getId(), true,intent.getStringExtra("description"),intent.getStringExtra("title"),intent.getStringExtra("link"));
                        isFind = true;
                    }
                }

                if (!isFind)
                    pendingNotification(context, intent);
        }
        else if (!intent.hasExtra("KEYSHARED")) {
            pendingNotification(context, intent);
        }



    }

    public void onUpdateDB(ReminderData data, Context context) {
        final mDataBase db = new mDataBase(context);
        db.createDB();

        String update = "UPDATE " + db.Reminder_tbl + " SET " + db.ColActive + " = " + data.getFlag() + " WHERE " + db.ColID + " = " + data.getId();
        db.getWritableDatabase().execSQL(update);
        db.close();

    }

    public void setNotifSound(String type) {
        switch (type) {
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

}
