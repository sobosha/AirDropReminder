package com.diacoipj.airdropreminder.Main;

public class ResponseObject {
    boolean success;
    int itamID;

    public int getItemID() {
        return itamID;
    }

    public void setItemID(int itemID) {
        this.itamID = itemID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
