package com.diacoipj.airdropreminder._Core.respons.Reminders;

import com.diacoipj.airdropreminder.Setting.Setting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReminderData implements Serializable,Comparable<ReminderData> {
    int id;
    int flag;// 1 active  - 2 deactive - 3 done
    String text ="Remidner" , note;
    String alertHour;
    String alertDate = "";
    String backColor , backColor2 ;
    String type; //  2 AIRDROP -   1 IDO
    int sound = 2;
    String tag="other" ;
    List<String> daysReminderList =new ArrayList<>();
    String daysReminder ;
    String siteName, value, submitDate;
    String walletType ="";
    String discordLink="";
    String twitter="";
    String chainType ="";

    public String getCahinType() {
        return chainType ;
    }

    public void setCahinType(String cahinType) {
        this.chainType  = cahinType;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getDiscordLink() {
        return discordLink;
    }

    public void setDiscordLink(String discordLink) {
        this.discordLink = discordLink;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }



    public ReminderData() {
    }

    public ReminderData(int id, int flag, String text, String note, String alertHour, String alertDate, String backColor, String backColor2, String type, int sound, String tag, List<String> daysReminderList, String daysReminder, String siteName, String value, String submitDate) {
        this.id = id;
        this.flag = flag;
        this.text = text;
        this.note = note;
        this.alertHour = alertHour;
        this.alertDate = alertDate;
        this.backColor = backColor;
        this.backColor2 = backColor2;
        this.type = type;
        this.sound = sound;
        this.tag = tag;
        this.daysReminderList = daysReminderList;
        this.daysReminder = daysReminder;
        this.siteName = siteName;
        this.value = value;
        this.submitDate = submitDate;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteName;
    }

    public void setSiteAddress(String siteAddress) {
        siteName = siteAddress;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAlertHour() {
        return alertHour;
    }

    public void setAlertHour(String alertHour) {
        this.alertHour = alertHour;
    }

    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(String alertDate) {
        this.alertDate = alertDate;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getBackColor2() {
        return backColor2;
    }

    public void setBackColor2(String backColor2) {
        this.backColor2 = backColor2;
    }

    public int getType() {
        if(type.equals("AIRDROP"))
        return 2;
        else
            return 1;
    }

    public void setType(int type) {
        if(type ==2)
            this.type = "AIRDROP";
        else
            this.type = "IDO";
    }

    public void setType(String isAirDrop) {
        this.type = isAirDrop;
    }


    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getDaysReminderList() {
        return daysReminderList;
    }

    public void setDaysReminderList(List<String> daysReminderList) {
        this.daysReminderList = daysReminderList;
    }

    public String getDaysReminder() {
        return daysReminder;
    }

    public void setDaysReminder(String daysReminder) {
        this.daysReminder = daysReminder;
    }


    @Override
    public int compareTo(ReminderData o) {
        Setting s=new Setting();
        int hourCurrent= Integer.parseInt(alertHour.split(":")[0]);
        int minCurrent=Integer.parseInt(alertHour.split(":")[1]);
        int hourNext= Integer.parseInt(o.alertHour.split(":")[0]);
        int minNext=Integer.parseInt(o.alertHour.split(":")[1]);
        if(s.CompareTwoDateMiladi(o.alertDate,alertDate)==0){
            if(hourCurrent>hourNext)
                return 1;
            else if(hourCurrent==hourNext)
            {
                if(minCurrent>=minNext)
                    return 1;
                else return -1;
            }
            else
                return -1;
        }
        else if(s.CompareTwoDateMiladi(o.alertDate,alertDate)==1){
            return -1;
        }
        else{
            return 1;
        }

    }
}
