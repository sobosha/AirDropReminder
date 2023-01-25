package com.diacoipj.airdropreminder._Core.DB;

import android.content.ContentValues;
import android.content.Context;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

public class SaveInDB {

    Context context ;
    String date ;
    public Context getContext() {
        return context;
    }

    public SaveInDB(Context context,String date) {
        this.context = context;
        this.date = date;
    }

    public void ReminderSaveInDB(ReminderData data , int type , boolean isUpate) {
        final mDataBase db = new mDataBase(context);
        db.createDB();
        ContentValues cv = new ContentValues();
        cv.put(db.ColID, data.getId());
        cv.put(db.ColActive, data.getFlag());
        cv.put(db.ColTitle, data.getText());

        cv.put(db.ColDate, new Setting().toOrganizeDate(data.getAlertDate()));
        cv.put(db.ColTime, data.getAlertHour());
        cv.put(db.ColSound , data.getSound());
        if(type==-1)
            cv.put(db.ColType, data.getType());
        else
            cv.put(db.ColType ,type);
        if(!isUpate) {
            db.insert(db.Reminder_tbl, cv, null);
        } else {
            db.update(db.Reminder_tbl, cv , db.ColID , String.valueOf(data.getId()),null);
        }
        db.close();
    }


}
