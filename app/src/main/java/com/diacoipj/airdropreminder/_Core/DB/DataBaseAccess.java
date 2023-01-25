package com.diacoipj.airdropreminder._Core.DB;

import android.content.Context;
import android.database.Cursor;

import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class DataBaseAccess {



    String[] days;
    List<String> dayLists = new ArrayList<>();
    public  void setReminders(Context context , List<ReminderData> data , int isIDo){
        final mDataBase db = new mDataBase(context);
        db.createDB();
        Cursor cursor;
        if(isIDo!=-1) {
            if(isIDo==2) {
                cursor = db.ReadFromDB("select * from " + db.Reminder_tbl + " where " + db.ColType + " = " + isIDo
                        +" order by "+ db.ColActive+","+db.ColDate + " desc" );
            }
            else
             cursor = db.ReadFromDB("select * from " + db.Reminder_tbl + " where " + db.ColType + " = " + isIDo +" order by "+ db.ColActive + " asc" );
        } else {
            cursor = db.ReadFromDB("select * from " + db.Reminder_tbl +" order by "+ db.ColActive + " asc "  );
        }
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            while (!cursor.isAfterLast()){
                ReminderData sp = new ReminderData();
                Gson gson = new Gson();
                dayLists = new ArrayList<>();
                days = gson.fromJson(cursor.getString(cursor.getColumnIndex(db.ColDays)), String[].class);
                if (days != null && dayLists != null) {
                    dayLists = Arrays.asList(days);
                }
                sp.setId(cursor.getInt(0));
                sp.setFlag(cursor.getInt(1));
                sp.setText(cursor.getString(2));
                sp.setAlertDate(cursor.getString(3));
                sp.setAlertHour(cursor.getString(4));
                sp.setType(cursor.getInt(5));
                sp.setSound(cursor.getInt(6));
                sp.setBackColor(cursor.getString(7));
                sp.setBackColor2(cursor.getString(8));
                sp.setNote(cursor.getString(9));
                sp.setTag(cursor.getString(10));
                sp.setDaysReminderList(dayLists);
                sp.setSiteAddress(cursor.getString(cursor.getColumnIndex(db.ColSiteAddress)));
                sp.setValue(cursor.getString(cursor.getColumnIndex(db.ColAvgMoney)));
                sp.setSubmitDate(cursor.getString(cursor.getColumnIndex(db.ColSignIn)));
                data.add(sp);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

    }


}
