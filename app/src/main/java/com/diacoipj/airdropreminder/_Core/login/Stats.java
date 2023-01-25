package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("stars")
    private int stars;

    @JsonProperty("league")
    private String league;

    @JsonProperty("league_color")
    private String league_color;

    @JsonProperty("league_logo")
    private String league_logo;

    @JsonProperty("league_logo_v2")
    private String league_logo_v2;

    @JsonProperty("prize")
    private String prize;

    @JsonProperty("prizeModel")
    private String prizeModel;


    @JsonProperty("current")
    private int current;


    @JsonProperty("offset")
    private int offset;

    @JsonProperty("max")
    private int max;

    public String getLeague_color() {
        return league_color;
    }

    public String getLeague_logo() {
        return league_logo;
    }

    public int getStars() {
        return stars;
    }

    public String getLeague() {
        return league;
    }

    public String getPrize() {
        return prize;
    }

    public int getCurrent() {
        return current;
    }

    public int getMax() {
        return max;
    }

    public String getPrizeModel() {
        return prizeModel;
    }

    public int getOffset() {
        return offset;
    }

    public String getLeague_logo_v2() {
        return league_logo_v2;
    }
}
