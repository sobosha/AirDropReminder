package com.diacoipj.airdropreminder.Setting;

import android.content.Context;
import android.view.View;
import android.widget.NumberPicker;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.DatesCallBack;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.tools.DayEntity;
import com.diacoipj.airdropreminder.tools.PersianDate;
import com.diacoipj.airdropreminder.tools.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateManager {
    public String getTodayDate(boolean isAlphabet, boolean isMonthYear, String month) {
        Date todayDate = Calendar.getInstance().getTime();
        String shamsi ;
        String alphabet ;
        DateShamsi datehamsi = new DateShamsi(todayDate);
        shamsi = datehamsi.year + "/" + datehamsi.month + "/" + datehamsi.date;
        alphabet = MainActivity.getGlobal().getResources().getString(R.string.ToDay) + "، " + datehamsi.date + " " + datehamsi.persianMonth(datehamsi.month);
        nValue.getGlobal().setCurrentDate(datehamsi.year + "/" + datehamsi.month + "/" + datehamsi.date);
        if (Setting.isEnLang()) {
            int [] miladiDate = new DateMiladi().toGregorian(datehamsi.year , datehamsi.month , datehamsi.date);
            shamsi = miladiDate[0] + "/" + miladiDate[1] + "/" + miladiDate[2];
            alphabet = MainActivity.getGlobal().getResources().getString(R.string.ToDay) + "، " + miladiDate[0] + " " + datehamsi.persianMonth(miladiDate[1]);
        }
        if (isAlphabet) {
            if (!isMonthYear)
                return alphabet;
            else {
                String date[] = month.split("@@");
                return datehamsi.persianMonth(Integer.parseInt(date[1])) + " " + Integer.parseInt(date[0]);
            }
        } else
            return shamsi;
    }


    public String getTodayDateForNotif(boolean slash) {
        Date todayDate = Calendar.getInstance().getTime();
        DateShamsi datehamsi = new DateShamsi(todayDate);
        final String alphabet = slash ? datehamsi.year + "/" + datehamsi.month + "/" + datehamsi.date : datehamsi.year + "" + datehamsi.month + "" + datehamsi.date;
        return alphabet;
    }

    String alphabet;

    public String getTodayDate(int day, int month, int year) {
        DateShamsi datehamsi = new DateShamsi();
        if (day == -1)
            alphabet = datehamsi.persianMonth(month) + " " + year;
        else alphabet = day + " " + datehamsi.persianMonth(month) + " " + year;

        return alphabet;
    }

    public static int currentYear(Context context) {
        Utils utils = Utils.getInstance(context);
        utils.clearYearWarnFlag();
        com.diacoipj.airdropreminder.tools.PersianDate persianDate = utils.getToday();
        return persianDate.getYear();
    }

    public int currentMonth(Context context) {
        Utils utils = Utils.getInstance(context);
        utils.clearYearWarnFlag();
        com.diacoipj.airdropreminder.tools.PersianDate persianDate = utils.getToday();
        return persianDate.getMonth();
    }
    public static String TodayMiladi(Context context) {
        Utils utils = Utils.getInstance(context);
        utils.clearYearWarnFlag();
        PersianDate persianDate = utils.getToday();
        return new DateShamsi().persianMonth(persianDate.getMonth())+persianDate.getDayOfMonth();
    }

    public static Date Today() {
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            time = getPlusIranTime(simpleDateFormat.parse(simpleDateFormat.format(time)));
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
    }

    public static Date Tommorow(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(getDatePart(date).getTime());
        instance.add(5, i);
        return instance.getTime();
    }

    public static Date getPlusIranTime(Date date) {
        date.setTime(date.getTime() + 16260000);
        return date;
    }
    private static Calendar getDatePart(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 4);
        instance.set(12, 31);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance;
    }

    public  static String[] MonthNames(Context context){
        if (mLocalData.getLanguage(context).equals("fa"))
            return  new String[]{"فروردین", "اردیبهشت", "خرداد", "تیر", " مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
        else if (mLocalData.getLanguage(context).equals("en"))
            return new String[]{"Jan", "Feb", "March", "April", " May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        else
            return  new String[]{"حمل", "ثور", "جوزا", "سرطان", " اسد", "سنبله", "میزان", "عقرب", "قوس", "جدی", "دلو", "حوت"};

    }

    public static String toOrganizeDateStatic(String date) {
        if (date != null && date.length() > 0) {
            String[] split = date.split(date.contains("/") ? "/" : "-");
            String month = Integer.parseInt(split[1]) <= 9 && !split[1].startsWith("0") ? "0" + split[1] : split[1];
            String day = Integer.parseInt(split[2]) <= 9 && !split[2].startsWith("0") ? "0" + split[2] : split[2];
            if (date.contains("/"))
                return split[0] + "/" + month + "/" + day;
            else
                return split[0] + "-" + month + "-" + day;
        }
        return date;
    }

    List<String> years = new ArrayList<>();
    List<String> days = new ArrayList<>();
    String[] yearsList;
    String[] daysList = new String[31];
    String[] monthList = new String[12];
    int month = 1, day = 1, year = 1399;
    String min = "00", hour = "00";
    List<DayEntity> Current;
    List<DayEntity> Prev;
    String[] now;
    private Utils utils;
    int max;
    String date;

    public void onSetDatePicker(Context context, View numberPickerYear, View numberPickerMonth, View numberPickerDay, boolean moreDate, int numberOfYear, boolean addMoreDateThanToday, DatesCallBack datesCallBack) {
        yearsList = new String[numberOfYear];
        ((NumberPicker) numberPickerYear).setDisplayedValues(null);
        ((NumberPicker) numberPickerMonth).setDisplayedValues(null);
        ((NumberPicker) numberPickerDay).setDisplayedValues(null);
        date = new DateManager().getTodayDate(false, false, "");
        now = date.split(date.contains("/") ? "/" : "-");
        utils = Utils.getInstance(context);
        max = utils.getDays(0).get(0).getPersianDate().getYear() - (moreDate ? 0 : 1);
        year = max;
        Current = utils.getDays(0);
        Prev = utils.getDays(1);
        for (int i = 0; i < numberOfYear; i++) {
            years.add(String.valueOf(addMoreDateThanToday ? max + i : max - i));
            yearsList[i] = years.get(i);
        }

        for (int i = 0; i < 31; i++) {
            if (i < 9 && i != 0)
                days.add(("" + (i + 1)));
            else days.add(String.valueOf(i + 1));
            daysList[i] = days.get(i);
        }
        month = Current.get(0).getPersianDate().getMonth();
        day = Integer.parseInt(now[2]);
        monthList = MonthNames(context);

        createNumPickerWithStringVals((NumberPicker) numberPickerMonth, 1, 12, monthList);
        ((NumberPicker) numberPickerMonth).setOnValueChangedListener((numberPicker, i, i1) -> {
            month = i1;
            datesCallBack.month(month);
        });
        ((NumberPicker) numberPickerMonth).setValue(Integer.parseInt(now[1]));


//        ----------------------------year---------------------------------------
        createNumPickerWithStringVals((NumberPicker) numberPickerYear, 1, numberOfYear, yearsList);
        ((NumberPicker) numberPickerYear).setOnValueChangedListener((numberPicker, i, i1) -> {
            year = addMoreDateThanToday ? max + i1 - 1 : max - i1 + 1;
            datesCallBack.year(year);
        });

//        ------------------------------day--------------------------------------
        createNumPickerWithStringVals((NumberPicker) numberPickerDay, 1, 31, daysList);
        ((NumberPicker) numberPickerDay).setOnValueChangedListener((numberPicker, i, i1) -> {
            day = i1;
            datesCallBack.day(day);
        });
        ((NumberPicker) numberPickerDay).setValue(Integer.parseInt(now[2]));

        datesCallBack.year(max + ((NumberPicker) numberPickerYear).getValue() - 1);
        datesCallBack.month(((NumberPicker) numberPickerMonth).getValue());
        datesCallBack.day(((NumberPicker) numberPickerDay).getValue());

    }

    public void createNumPickerWithStringVals(NumberPicker numberPickers, int minValue, int maxValue, String[] displayValue) {
        numberPickers.setMinValue(minValue);
        numberPickers.setMaxValue(maxValue);
        numberPickers.setDisplayedValues(displayValue);
    }

    public static String[] generateNumbers(int start, int end, String[] array, List<String> list, int changePicker, String addZero, String paswand) {
        for (int i = start; i < end; i++) {
            if (i < 9)
                list.add((addZero + (i + changePicker)) + paswand);
            else
                list.add((i + changePicker) + paswand);
            array[i] = list.get(i);
        }
        return array;
    }


}
