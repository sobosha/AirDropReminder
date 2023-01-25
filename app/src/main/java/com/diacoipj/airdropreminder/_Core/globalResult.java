package com.diacoipj.airdropreminder._Core;

public class globalResult {

    int status ;
    private boolean success  , package_active;
    String instagram , message ;
    int artice_id , news=0 ;
    String img ,ad="tapsell";

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public String getInstagram() {
        return instagram;
    }

    public int getArtice_id() {
        return artice_id;
    }


    public boolean isPackage_active() {
        return package_active;
    }

    public void setPackage_active(boolean package_active) {
        this.package_active = package_active;
    }

    public String getImg() {
        return img;
    }

    public int getNews() {
        return news;
    }

    public String getAd() {
        return ad;
    }

    public String getMessage() {
        return message;
    }
}
