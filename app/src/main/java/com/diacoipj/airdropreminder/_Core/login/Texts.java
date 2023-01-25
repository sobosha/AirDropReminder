package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Texts {

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("enter_code_val")
    private String enter_code_val;

    @JsonProperty("enter_code_type")
    private String enter_code_type;

    @JsonProperty("enter_mobile_val")
    private String enter_mobile_val;

    @JsonProperty("enter_mobile_type")
    private String enter_mobile_type;

    @JsonProperty("enter_name_type")
    private String enter_name_type;

    @JsonProperty("enter_name_val")
    private String enter_name_val;

    public String getEnter_code_val() {
        return enter_code_val;
    }

    public String getEnter_code_type() {
        return enter_code_type;
    }

    public String getEnter_mobile_val() {
        return enter_mobile_val;
    }

    public String getEnter_mobile_type() {
        return enter_mobile_type;
    }

    public String getEnter_name_type() {
        return enter_name_type;
    }

    public String getEnter_name_val() {
        return enter_name_val;
    }

    @JsonProperty("needToRegister")

    private String needToRegister;

    public String getIntro() {
        return intro;
    }

    public String getNeedToRegister() {
        return needToRegister;
    }
}
