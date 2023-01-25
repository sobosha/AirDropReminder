package com.diacoipj.airdropreminder._Core.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item_type_list {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("txtBuy")
    private String txtBuy;

    @JsonProperty("type")
    private String type;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("isVisible")
    private boolean isVisible;

    @JsonProperty("qIcon")
    private String qIcon;

    @JsonProperty("price")
    private int price;

    @JsonProperty("bannerActive")
    private int bannerActive;

    @JsonProperty("fullPrice")
    private int fullPrice;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("bannerUrl")
    private String bannerUrl;

    @JsonProperty("hallName")
    private String hallName;

    @JsonProperty("shopText")
    private String shopText;

    @JsonProperty("shopIcon")
    private String shopIcon;

    @JsonProperty("colorSp1")
    private String colorSp1;

    @JsonProperty("colorSp2")
    private String colorSp2;

    @JsonProperty("giftVal")
    private int giftVal;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getBannerActive() {
        return bannerActive;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public String getIcon() {
        return icon;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public String getShopText() {
        return shopText;
    }

    public String getHallName() {
        return hallName;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public String getqIcon() {
        return qIcon;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public int getGiftVal() {
        return giftVal;
    }

    public String getColorSp1() {
        return colorSp1;
    }

    public String getColorSp2() {
        return colorSp2;
    }

    public String getTxtBuy() {
        return txtBuy;
    }
}
