package com.diacoipj.airdropreminder.tools;


public class DayEntity {
    private String num;
    private boolean holiday;
    private boolean today;
    private int dayOfWeek;
    private PersianDate persianDate;
    int hasEvent ;
    int specificationID=-1 ;



    /*-----------------------*/
    private boolean pr;
    public  boolean isFirstP ;
    public  boolean isEndP;

    /*----------------------- withe between pr and barvari ----------------*/
    public  boolean withBeforeFertility ;
    /*------------------------ barvari -------------------------------*/
    public  boolean fertility ;
    public  boolean isFirstF ;
    public  boolean isEndF;
    public  boolean isHunderedPercent;
    /*------------------------ withe between barvari and pms -------------------------------*/
    public  boolean withBeforePms ;

    /*-------------------------- pms ------------------------------------------*/
    public  boolean pms ;
    public  boolean isFirstPMS ;
    public  boolean isEndPms ;



    /*-----------------------*/

    public boolean isFirstP() {
        return isFirstP;
    }

    public void setFirstP(boolean firstP) {
        isFirstP = firstP;
    }

    public boolean isEndP() {
        return isEndP;
    }

    public void setEndP(boolean endP) {
        isEndP = endP;
    }

    public boolean isPr() {
        return pr;
    }

    public void setPr(boolean pr) {
        this.pr = pr;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public PersianDate getPersianDate() {
        return persianDate;
    }

    public void setPersianDate(PersianDate persianDate) {
        this.persianDate = persianDate;
    }

     public boolean isWithBeforeFertility() {
        return withBeforeFertility;
    }

    public void setWithBeforeFertility(boolean withBeforeFertility) {
        this.withBeforeFertility = withBeforeFertility;
    }

    public boolean isFertility() {
        return fertility;
    }

    public void setFertility(boolean fertility) {
        this.fertility = fertility;
    }

    public boolean isFirstF() {
        return isFirstF;
    }

    public void setFirstF(boolean firstF) {
        isFirstF = firstF;
    }

    public boolean isEndF() {
        return isEndF;
    }

    public void setEndF(boolean endF) {
        isEndF = endF;
    }

    public boolean isWithBeforePms() {
        return withBeforePms;
    }

    public void setWithBeforePms(boolean withBeforePms) {
        this.withBeforePms = withBeforePms;
    }

    public boolean isPms() {
        return pms;
    }

    public void setPms(boolean pms) {
        this.pms = pms;
    }

    public boolean isFirstPMS() {
        return isFirstPMS;
    }

    public void setFirstPMS(boolean firstPMS) {
        isFirstPMS = firstPMS;
    }

    public boolean isEndPms() {
        return isEndPms;
    }

    public void setEndPms(boolean endPms) {
        isEndPms = endPms;
    }

    public int getHasEvent() {
        return hasEvent;
    }

    public void setHasEvent(int hasEvent) {
        this.hasEvent = hasEvent;
    }

    public int getSpecificationID() {
        return specificationID;
    }

    public void setSpecificationID(int specificationID) {
        this.specificationID = specificationID;
    }

    public boolean isHunderedPercent() {
        return isHunderedPercent;
    }

    public void setHunderedPercent(boolean hunderedPercent) {
        this.isHunderedPercent = hunderedPercent;
    }
}
