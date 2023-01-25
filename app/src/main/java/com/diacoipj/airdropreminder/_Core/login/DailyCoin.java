package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyCoin {

    @JsonProperty("sec")
    private int sec;

    public int getSec() {
        return sec;
    }
}
