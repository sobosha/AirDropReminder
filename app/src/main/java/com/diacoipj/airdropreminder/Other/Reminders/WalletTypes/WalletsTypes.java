package com.diacoipj.airdropreminder.Other.Reminders.WalletTypes;

public class WalletsTypes {
    int id ;
    String name , type;
    boolean check ;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WalletsTypes(int id, String name ) {
        this.id = id;
        this.name = name;
    }
    public WalletsTypes(String name , String type) {
        this.name = name;
        this.type = type;
    }
}