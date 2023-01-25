package com.diacoipj.airdropreminder.Setting;

import com.diacoipj.airdropreminder.tools.Constants;

import java.util.Date;


public class DateShamsi {

    public String strWeekDay = "";
    public String strMonth = "";

    public int date;
    public int month;
    public int year;

    public DateShamsi() {
        Date MiladiDate = new Date();
        calcSolarCalendar(MiladiDate);
    }

    public String getFarsiDate() {
        return strWeekDay + " " + String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(date);
    }

    public DateShamsi(Date MiladiDate) {
     //   MiladiDate = new Date(MiladiDate.getTime() - TimeZone.getDefault().getRawOffset());
        calcSolarCalendar(MiladiDate);
    }

    private void calcSolarCalendar(Date MiladiDate) {

        int ld;
        int miladiYear = MiladiDate.getYear() + 1900;
        int miladiMonth = MiladiDate.getMonth() + 1;
        int miladiDate = MiladiDate.getDate();
        int WeekDay = MiladiDate.getDay();

        int[] buf1 = new int[12];
        int[] buf2 = new int[12];

        buf1[0] = 0;
        buf1[1] = 31;
        buf1[2] = 59;
        buf1[3] = 90;
        buf1[4] = 120;
        buf1[5] = 151;
        buf1[6] = 181;
        buf1[7] = 212;
        buf1[8] = 243;
        buf1[9] = 273;
        buf1[10] = 304;
        buf1[11] = 334;

        buf2[0] = 0;
        buf2[1] = 31;
        buf2[2] = 60;
        buf2[3] = 91;
        buf2[4] = 121;
        buf2[5] = 152;
        buf2[6] = 182;
        buf2[7] = 213;
        buf2[8] = 244;
        buf2[9] = 274;
        buf2[10] = 305;
        buf2[11] = 335;

        if ((miladiYear % 4) != 0) {
            date = buf1[miladiMonth - 1] + miladiDate;

            if (date > 79) {
                date = date - 79;
                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = date / 31;
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                    ld = 11;
                } else {
                    ld = 10;
                }
                date = date + ld;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }
        } else {
            date = buf2[miladiMonth - 1] + miladiDate;

            if (miladiYear >= 1996) {
                ld = 79;
            } else {
                ld = 80;
            }
            if (date > ld) {
                date = date - ld;

                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = (date / 31);
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                date = date + 10;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }

        }

        switch (month) {
            case 1:
                strMonth = "فروردين";
                break;
            case 2:
                strMonth = "ارديبهشت";
                break;
            case 3:
                strMonth = "خرداد";
                break;
            case 4:
                strMonth = "تير";
                break;
            case 5:
                strMonth = "مرداد";
                break;
            case 6:
                strMonth = "شهريور";
                break;
            case 7:
                strMonth = "مهر";
                break;
            case 8:
                strMonth = "آبان";
                break;
            case 9:
                strMonth = "آذر";
                break;
            case 10:
                strMonth = "دي";
                break;
            case 11:
                strMonth = "بهمن";
                break;
            case 12:
                strMonth = "اسفند";
                break;
        }

        switch (WeekDay) {

            case 0:
                strWeekDay = "يکشنبه";
                break;
            case 1:
                strWeekDay = "دوشنبه";
                break;
            case 2:
                strWeekDay = "سه شنبه";
                break;
            case 3:
                strWeekDay = "چهارشنبه";
                break;
            case 4:
                strWeekDay = "پنج شنبه";
                break;
            case 5:
                strWeekDay = "جمعه";
                break;
            case 6:
                strWeekDay = "شنبه";
                break;
        }

    }

    public String persianMonth(int month) {
        if (mLocalData.getLanguage(nValue.getGlobal().getContext()).equals("fa")) {
            switch (month) {
                case 1:
                    return "فروردين";
                case 2:
                    return "ارديبهشت";
                case 3:
                    return "خرداد";
                case 4:
                    return "تير";
                case 5:
                    return "مرداد";
                case 6:
                    return "شهريور";
                case 7:
                    return "مهر";
                case 8:
                    return "آبان";
                case 9:
                    return "آذر";
                case 10:
                    return "دي";
                case 11:
                    return "بهمن";
                case 12:
                    return "اسفند";
                default:
                    return "";
            }
        } else if(mLocalData.getLanguage(nValue.getGlobal().getContext()).equals("ps")) {
            switch (month) {
                case 1:
                    return "حمل";
                case 2:
                    return "ثور";
                case 3:
                    return "جوزا";
                case 4:
                    return "سرطان";
                case 5:
                    return "اسد";
                case 6:
                    return "سنبله";
                case 7:
                    return "میزان";
                case 8:
                    return "عقرب";
                case 9:
                    return "قوس";
                case 10:
                    return "جدی";
                case 11:
                    return "دلو";
                case 12:
                    return "حوت";
                default:
                    return "";
            }

        } else {
            switch (month) {
                case 1:
                    return "January";
                case 2:
                    return "February";
                case 3:
                    return "March";
                case 4:
                    return "April";
                case 5:
                    return "May";
                case 6:
                    return "June";
                case 7:
                    return "July";
                case 8:
                    return "August";
                case 9:
                    return "September";
                case 10:
                    return "October";
                case 11:
                    return "November";
                case 12:
                    return "December";
                default:
                    return "";
            }

        }
    }

    public static   String dayNum(String day){

       int a = 0;
        switch (day){
            case "ی": a=0;break;
            case "د": a=1;break;
            case "س": a=2;break;
            case "چ": a=3;break;
            case "پ": a=4;break;
            case "ج": a=5;break;
            case "ش": a=6;break;

        }
        return Constants.mFIRST_CHAR_OF_DAYS_OF_WEEK_NAME[a];

    }
}
