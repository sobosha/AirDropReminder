package com.diacoipj.airdropreminder._Core.respons.DR1;


public class DataModelResponse {

    boolean success ;
    int bz,cPost,cBookmark,cEvent ,userID;
    Data data;
    String text,link , tokenBot ,token;

    public String getToken() {
        return token;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getBz() {
        return bz;
    }

    public int getcPost() {
        return cPost;
    }

    public int getcBookmark() {
        return cBookmark;
    }

    public int getcEvent() {
        return cEvent;
    }

    public int getUserID() {
        return userID;
    }

    public Data getData() {
        return data;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getTokenBot() {
        return tokenBot;
    }
}
