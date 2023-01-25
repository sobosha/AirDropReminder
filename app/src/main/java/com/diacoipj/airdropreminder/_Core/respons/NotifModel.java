package com.diacoipj.airdropreminder._Core.respons;

public class NotifModel {
    int notifId ;
    String name , time , text , date ;
// postion = 0 -> pr ,  position = 1 -> fr , position = 2 -> pms , position = 3 -> pr2    ,   position = 4 -> fr2 ,  position = 5 -> pms2 , position = 7 -> hunderedPercentF


    public NotifModel(int notifId, String name, String time, String text , String date) {
        this.notifId = notifId;
        this.name = name;
        this.time = time;
        this.text = text;
        this.date = date;
    }

    public NotifModel(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NotifModel () {

    }

    public int getNotifId() {
        return notifId;
    }

    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
