package com.diacoipj.airdropreminder.Main;

import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.List;

public class ReminderList {
    ReminderDate data ;
    List<ReminderData> list;

    public List<ReminderData> getList() {
        return list;
    }

    public void setList(List<ReminderData> list) {
        this.list = list;
    }

    public ReminderDate getData() {
        return data;
    }
}
