package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {


    @JsonProperty("name")
    private String name ;

    @JsonProperty("cardNum")
    private String cardNum;

    @JsonProperty("mobile")
    private String mobile ;

    @JsonProperty("avatar")
    private String _avatar ;

    @JsonProperty("bz")
    private int bz ;

    @JsonProperty("buyWithWallet")
    private int buyWithWallet ;

    @JsonProperty("rules")
    private int rules ;

    @JsonProperty("tokens")
    private boolean tokens ;

    public boolean isSandbox() {
        return sandbox;
    }

    @JsonProperty("sandbox")
    private boolean sandbox ;



    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }


    public int getBuyWithWallet() {
        return buyWithWallet;
    }

    public int getBz() {
        return bz;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String get_avatar() {
        return _avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void set_avatar(String _avatar) {
        this._avatar = _avatar;
    }

    public void setBz(int bz) {
        this.bz = bz;
    }

    public boolean isTokens() {
        return tokens;
    }

    public int getRules() {
        return rules;
    }
}
