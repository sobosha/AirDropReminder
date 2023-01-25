package com.diacoipj.airdropreminder._Core.login;


import com.fasterxml.jackson.annotation.JsonProperty;

public class getNewUser {

    @JsonProperty("data")
    private LoginData data ;

    public LoginData getData() {
        return data;
    }
}
