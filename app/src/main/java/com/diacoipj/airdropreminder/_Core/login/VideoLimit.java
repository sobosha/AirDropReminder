package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoLimit {

    @JsonProperty("current")
    private int current ;

    @JsonProperty("max")
    private int max ;

    @JsonProperty("lockedSec")
    private int lockedSec ;


    public int getCurrent() {
        return current;
    }

    public int getMax() {
        return max;
    }

    public int getLockedSec() {
        return lockedSec;
    }
}

