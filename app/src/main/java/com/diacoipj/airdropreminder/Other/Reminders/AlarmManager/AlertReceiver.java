package com.diacoipj.airdropreminder.Other.Reminders.AlarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import com.diacoipj.airdropreminder.AlarmActivity;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlertReceiver extends BroadcastReceiver {

    public AlertReceiver() {

    }
    Calendar calendar = Calendar.getInstance();
    List<ReminderData> reminderData = new ArrayList<>();
    boolean isDays = false ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase("liomAlarm")){

            startMyActivity(context,intent);

        }
    }


    private void startMyActivity (Context context,Intent temp) {
        Intent i = new Intent(context, AlarmActivity.class);
        i.putExtra("description",temp.getStringExtra("description"));
        i.putExtra("Time",temp.getStringExtra("Time"));
        i.putExtra("title",temp.getStringExtra("title"));
        i.putExtra("link",temp.getStringExtra("link"));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
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
