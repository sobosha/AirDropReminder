package com.diacoipj.airdropreminder._Core.requset.User;

public class reqUserInfo {

    String name;
    int avatarId;
    String birthday,marital_status ,bio ;

    public reqUserInfo(String name, int avatarId, String birthday, String marital_status , String bio) {
        this.name = name;
        this.avatarId = avatarId;
        this.birthday = birthday;
        this.marital_status = marital_status;
        this.bio = bio ;
    }
}
