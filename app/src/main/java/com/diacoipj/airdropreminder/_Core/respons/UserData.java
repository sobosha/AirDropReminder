package com.diacoipj.airdropreminder._Core.respons;

import com.diacoipj.airdropreminder.Setting.Setting;

public class UserData
{
    int id;
    String name = ""
            , birthday =""
            , marital_status = "single"
            ,mobile=""
             , email =""
            ,length=""
            ,last_time=""
            ,_last_time=""
            ,cycle=""
            ,password=""
            ,country=""
            ,date_before_last_time=""
            ,_date_before_last_time="" ,
    bio="";

    int avatar  , socialAvatar ;


    String partnerName , marridDate  ,tokenBot;
    int  permision1 = 1 , permision2 =1 , permision3 ;
    String weight , height ;

    public UserData() { }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {

        return birthday!=null && !birthday.startsWith("0")?birthday:"";
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMarital_status() {
        if(marital_status==null)
            return "single";
        else
            return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getNumber() {
        return mobile;
    }

    public void setMobile(String number) {
        this.mobile = number;
    }


    public void setLength(String length) {
        this.length = length;
    }

    public void setLast_time(String last_time) {
        this.last_time =last_time!=null &&  last_time.length()>0 ?new Setting().toOrganizeDate(last_time) :last_time;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLength() {
        if(length==null||length.equals(""))
            return "0";
        else
             return length;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_time() {
        return new Setting().toOrganizeDate(last_time);
    }

    public String getCycle() {
        return cycle;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;

    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getSocialAvatar() {
        return socialAvatar;
    }

    public void setSocialAvatar(int socialAvatar) {
        this.socialAvatar = socialAvatar;
    }

    public String getDate_before_last_time() {
        if(date_before_last_time==null)
            return "";
        else
          return date_before_last_time;
    }

    public void setDate_before_last_time(String date_before_last_time) {
        this.date_before_last_time = date_before_last_time;
    }

    public String getPartnerName() {
        if (partnerName==null)
            return "";
        else
          return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getMarridDate() {
        if (marridDate==null)
            return "1399/01/01";
        else
        return marridDate;
    }

    public void setMarridDate(String marridDate) {
        this.marridDate = marridDate;
    }

    public String getTokenBot() {
        if (tokenBot==null)
            return "";
        else
        return tokenBot;
    }

    public void setTokenBot(String tokenBot) {
        this.tokenBot = tokenBot;
    }

    public int getPermision1() {
        return permision1;
    }

    public void setPermision1(int permision1) {
        this.permision1 = permision1;
    }

    public int getPermision2() {
        return permision2;
    }

    public void setPermision2(int permision2) {
        this.permision2 = permision2;
    }

    public int getPermision3() {
        return permision3;
    }

    public void setPermision3(int permision3) {
        this.permision3 = permision3;
    }

    public String getWeight() {
        return weight != null ? weight : "";
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height!=null ? height : "";
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String get_mlast_time() {
        return _last_time==null && last_time!=null? new Setting().toOrganizeDate(Setting.toMiladi(last_time)):_last_time;
    }

    public void set_mlast_time(String _last_time) {
        this._last_time =_last_time!=null &&  _last_time.length()>0 ?new Setting().toOrganizeDate(_last_time) :_last_time;
    }

    public String get_mdate_before_last_time() {
        return _date_before_last_time==null && date_before_last_time!=null
                ?new Setting().toOrganizeDate(Setting.toMiladi(date_before_last_time))
                :_date_before_last_time;
    }

    public void set_mdate_before_last_time(String _date_before_last_time) {
        this._date_before_last_time = _date_before_last_time;
    }

    public int getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}

