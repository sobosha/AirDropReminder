package com.diacoipj.airdropreminder._Core.requset;

public class UserCreate {


    String mobile ;
    int code ;


    public UserCreate(String email) {
        this.mobile = email;
    }

    public String getEmail() {
        return mobile;
    }

    public UserCreate(String email, int code) {
        this.mobile = email;
        this.code = code;
    }
}
