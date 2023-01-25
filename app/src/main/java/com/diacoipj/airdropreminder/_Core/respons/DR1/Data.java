package com.diacoipj.airdropreminder._Core.respons.DR1;


import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;
import com.diacoipj.airdropreminder._Core.respons.UserData;
import com.diacoipj.airdropreminder._Core.respons.updateData;

import java.util.List;


public class Data {
    String version  ;
    List<updateData> data ;
    List<ReminderData> general ;
    List<ReminderData> costume ;

    String message ;
    String token ;
    int userID ;
    UserData userInfo ;
    int port ;
    String userIP ;

    public int getUserID() {
        return userID;
    }



    public String getVersion() {
        return version;
    }

    public List<updateData> getData() {
        return data;
    }

    public List<ReminderData> getGeneral() {
        return general;
    }

    public List<ReminderData> getCostume() {
        return costume;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public UserData getUserInfo() {
        return userInfo;
    }

    public int getPort() {
        return port;
    }

    public String getUserIP() {
        return userIP;
    }
}
