package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {

    @JsonProperty("silver")
    private int silver ;

    @JsonProperty("golden")
    private int golden ;

    @JsonProperty("wallet")
    private int wallet ;

    public int getSilver() {
        return silver;
    }

    public int getGolden() {
        return golden;
    }

    public int getWallet() {
        return wallet;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public void setGolden(int golden) {
        this.golden = golden;
    }
}
