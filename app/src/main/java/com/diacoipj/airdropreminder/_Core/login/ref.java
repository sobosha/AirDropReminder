package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ref
{

    @JsonProperty("refCoin")
    private String refCoin;

    @JsonProperty("referCoin")
    private String referCoin;

    @JsonProperty("refCode")
    private String refCode;

    @JsonProperty("refCoin_type")
    private String refCoin_type;

    @JsonProperty("referCoin_type")
    private String referCoin_type;

    @JsonProperty("refText")
    private String refText;

    @JsonProperty("referText")
    private String referText;

    @JsonProperty("txtRefAction")
    private String txtRefAction; // از نسخه پنج ب بالا این ابجکت رو نداریم

    @JsonProperty("refLink")
    private String refLink;

    public String getRefCoin() {
        return refCoin;
    }

    public String getReferCoin() {
        return referCoin;
    }

    public String getRefCode() {
        return refCode;
    }

    public String getRefText() {
        return refText;
    }

    public String getRefCoin_type() {
        return refCoin_type;
    }

    public String getReferCoin_type() {
        return referCoin_type;
    }

    public String getReferText() {
        return referText;
    }

    public String getRefLink() {
        return refLink;
    }
}
