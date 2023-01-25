package com.diacoipj.airdropreminder.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.widget.TextView;


import androidx.annotation.RawRes;


import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mLocalData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import static com.diacoipj.airdropreminder.tools.Constants.ARABIC_DIGITS;
import static com.diacoipj.airdropreminder.tools.Constants.DEFAULT_APP_LANGUAGE;
import static com.diacoipj.airdropreminder.tools.Constants.DEFAULT_IRAN_TIME;
import static com.diacoipj.airdropreminder.tools.Constants.DEFAULT_PERSIAN_DIGITS;
import static com.diacoipj.airdropreminder.tools.Constants.DEFAULT_WIDGET_IN_24;
import static com.diacoipj.airdropreminder.tools.Constants.FONT_PATH;
import static com.diacoipj.airdropreminder.tools.Constants.LIGHT_THEME;
import static com.diacoipj.airdropreminder.tools.Constants.PERSIAN_COMMA;
import static com.diacoipj.airdropreminder.tools.Constants.PERSIAN_DIGITS;
import static com.diacoipj.airdropreminder.tools.Constants.PREF_APP_LANGUAGE;
import static com.diacoipj.airdropreminder.tools.Constants.PREF_IRAN_TIME;
import static com.diacoipj.airdropreminder.tools.Constants.PREF_PERSIAN_DIGITS;
import static com.diacoipj.airdropreminder.tools.Constants.PREF_THEME;
import static com.diacoipj.airdropreminder.tools.Constants.PREF_WIDGET_IN_24;
import static com.diacoipj.airdropreminder.tools.DateConverter.civilToPersian;

public class Utils {

    private final String TAG = Utils.class.getName();
    private Context context;
    private Typeface typeface;
    private SharedPreferences prefs;

    private List<EventEntity> events;

    private String[] persianMonths;
    private String[] islamicMonths;
    private String[] gregorianMonths;
    private String[] weekDays;

    private Utils(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        updateStoredPreference();
    }

    private static WeakReference<Utils> myWeakInstance;

    public static Utils getInstance(Context context) {
        if (myWeakInstance == null || myWeakInstance.get() == null) {
            myWeakInstance = new WeakReference<>(new Utils(context.getApplicationContext()));
        }
        return myWeakInstance.get();
    }

    /**
     * Text shaping is a essential thing on supporting Arabic script text on older Android versions.
     * It converts normal Arabic character to their presentation forms according to their position
     * on the text.
     *
     * @param text Arabic string
     * @return Shaped text
     */
    public String shape(String text) {
        return (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB)
                ? ArabicShaping.shape(text)
                : text;
    }

    public String programVersion() {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Name not found on PersianCalendarUtils.programVersion");
            return "";
        }
    }

    private void initTypeface() {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH);
        }
    }

    public void setFont(TextView textView) {
        initTypeface();
        textView.setTypeface(typeface);
    }

    public void setFontAndShape(TextView textView) {
        setFont(textView);
        textView.setText(shape(textView.getText().toString()));
    }


    public void setActivityTitleAndSubtitle(Activity activity, String title, String subtitle ,TextView textView) {
        if (title == null || subtitle == null) {
            return;
        }
        initTypeface();
        SpannableString titleSpan = new SpannableString(shape(title));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            titleSpan.setSpan(new TypefaceSpan(typeface), 0, titleSpan.length(), 0);
        }
        titleSpan.setSpan(new RelativeSizeSpan(0.8f), 0, titleSpan.length(), 0);
        textView.setText( shape(title)+" "+shape(subtitle));
    }

    private char[] preferredDigits;
    private boolean clockIn24;
    public boolean iranTime;

    public void updateStoredPreference() {
        if(Setting.isEnLang()){
            preferredDigits = ARABIC_DIGITS;
        }else {
            preferredDigits = isPersianDigitSelected()
                    ? PERSIAN_DIGITS
                    : ARABIC_DIGITS;
        }

        clockIn24 = prefs.getBoolean(PREF_WIDGET_IN_24, DEFAULT_WIDGET_IN_24);
        iranTime = prefs.getBoolean(PREF_IRAN_TIME, DEFAULT_IRAN_TIME);
    }

    public boolean isPersianDigitSelected() {
        return prefs.getBoolean(PREF_PERSIAN_DIGITS, DEFAULT_PERSIAN_DIGITS);
    }


    public String getAppLanguage() {
        String language = prefs.getString(PREF_APP_LANGUAGE, DEFAULT_APP_LANGUAGE);
        // If is empty for whatever reason (pref dialog bug, etc), return Persian at least
        return TextUtils.isEmpty(language) ? DEFAULT_APP_LANGUAGE : language;
    }

    public String getTheme() {
        return prefs.getString(PREF_THEME, LIGHT_THEME);
    }


    public PersianDate getToday() {
        if(!Setting.isEnLang()) {
            return DateConverter.civilToPersian(new CivilDate(makeCalendarFromDate(new Date())));
        }else {
            return civilToPersian(new CivilDate(makeCalendarFromDate(new Date())));
        }
    }

    public Calendar makeCalendarFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (iranTime) {
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
        }
        calendar.setTime(date);
        return calendar;
    }


    public String formatNumber(int number) {
        return formatNumber(Integer.toString(number));
    }

    public String formatNumber(String number) {
        if (preferredDigits == ARABIC_DIGITS)
            return number;

        char[] result = number.toCharArray();
        for (int i = 0; i < result.length; ++i) {
            char c = number.charAt(i);
            if (Character.isDigit(c))
                result[i] = preferredDigits[Character.getNumericValue(c)];
        }
        return String.valueOf(result);
    }

    // /*تاریخی ک کلیک کردم و پایین نوشته میشه رو این قسمت بهم میده */
    public String dateToString(AbstractDate date) {
        return formatNumber(date.getDayOfMonth()) + ' ' + getMonthName(date) + ' ' +
                formatNumber(date.getYear());
    }

    public  String month(AbstractDate date){
        return formatNumber(getMonthName(date));
    }
    public String year(AbstractDate date) {
        return formatNumber(date.getYear());
    }


    public String dayTitleSummary(PersianDate persianDate) {
        return getWeekDayName(persianDate) + PERSIAN_COMMA + " " + dateToString(persianDate);
    }

    public String[] monthsNamesOfCalendar(AbstractDate date) {
        // the next step would be using them so lets check if they have initialized already
        if (persianMonths == null || gregorianMonths == null || islamicMonths == null)
            loadLanguageResource();

        if (date instanceof PersianDate)
            return persianMonths.clone();
        else if (date instanceof IslamicDate)
            return islamicMonths.clone();
        else
            return gregorianMonths.clone();
    }

    public String getMonthName(AbstractDate date) {
        return monthsNamesOfCalendar(date)[date.getMonth() - 1];
    }

    public String getWeekDayName(AbstractDate date) {
        /*if (date instanceof IslamicDate)
            date = DateConverter.islamicToCivil((IslamicDate) date);
        else */if (date instanceof PersianDate)
            date = DateConverter.persianToCivil((PersianDate) date);

        if (weekDays == null)
            loadLanguageResource();

        return weekDays[date.getDayOfWeek() % 7];
    }



    private String readStream(InputStream is) {
        // http://stackoverflow.com/a/5445161
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String readRawResource(@RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    private void loadEvents() {
        List<EventEntity> events = new ArrayList<>();
        try {
            JSONArray days = new JSONObject(readRawResource(R.raw.events)).getJSONArray("events");

            int length = days.length();
            for (int i = 0; i < length; ++i) {
                JSONObject event = days.getJSONObject(i);

                int year = event.getInt("year");
                int month = event.getInt("month");
                int day = event.getInt("day");
                String title = event.getString("title");
                boolean holiday = event.getBoolean("holiday");

                events.add(new EventEntity(new PersianDate(year, month, day), title, holiday));
            }

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        this.events = events;
    }

    private int maxSupportedYear = -1;
    private int minSupportedYear = -1;
    private boolean isYearWarnGivenOnce = false;

    public void checkYearAndWarnIfNeeded(int selectedYear) {
        // once is enough, see #clearYearWarnFlag() also
        if (isYearWarnGivenOnce)
            return;

        if (maxSupportedYear == -1 || minSupportedYear == -1)
            loadMinMaxSupportedYear();

        if (selectedYear < minSupportedYear) {

            isYearWarnGivenOnce = true;
        }

        if (selectedYear > maxSupportedYear) {
            isYearWarnGivenOnce = true;
        }
    }

    // called from CalendarFragment to make it once per calendar view
    public void clearYearWarnFlag() {
        isYearWarnGivenOnce = false;
    }

    private void loadMinMaxSupportedYear() {
        if (events == null) {
            loadEvents();
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (EventEntity eventEntity : events) {
            int year = eventEntity.getDate().getYear();

            if (min > year && year != -1) {
                min = year;
            }

            if (max < year) {
                max = year;
            }
        }

        minSupportedYear = currentYear()-1;
        maxSupportedYear = currentYear()+1;
    }

    public  int currentYear(){
        PersianDate persianDate = getToday();
        return persianDate.getYear();
    }


    public List<EventEntity> getEvents(PersianDate day) {
        if (events == null) {
            loadEvents();
        }

        List<EventEntity> result = new ArrayList<>();
        for (EventEntity eventEntity : events) {
            if (eventEntity.getDate().equals(day)) {
                result.add(eventEntity);
            }
        }
        return result;
    }

    public String getEventsTitle(PersianDate day, boolean holiday) {
        String titles = "";
        boolean first = true;
        List<EventEntity> dayEvents = getEvents(day);

        for (EventEntity event : dayEvents) {
            if (event.isHoliday() == holiday) {
                if (first) {
                    first = false;

                } else {
                    titles = titles + "\n";
                }
                titles = titles + event.getTitle();
            }
        }
        return titles;
    }


    public void loadLanguageResource() {
        @RawRes int messagesFile;
        String lang = getAppLanguage();

        if(mLocalData.getLanguage(MainActivity.getGlobal()).equals("fa"))
            messagesFile = R.raw.messages_fa;
        else if(mLocalData.getLanguage(MainActivity.getGlobal()).equals("en"))
            messagesFile = R.raw.messages_en;
        else
            messagesFile = R.raw.messages_fa_af;


        persianMonths = new String[12];
        islamicMonths = new String[12];
        gregorianMonths = new String[12];
        weekDays = new String[7];

        try {
            JSONObject messages = new JSONObject(readRawResource(messagesFile));

            JSONArray persianMonthsArray = messages.getJSONArray("PersianCalendarMonths");
            for (int i = 0; i < 12; ++i)
                persianMonths[i] = persianMonthsArray.getString(i);

            JSONArray islamicMonthsArray = messages.getJSONArray("IslamicCalendarMonths");
            for (int i = 0; i < 12; ++i)
                islamicMonths[i] = islamicMonthsArray.getString(i);

            JSONArray gregorianMonthsArray = messages.getJSONArray("GregorianCalendarMonths");
            for (int i = 0; i < 12; ++i)
                gregorianMonths[i] = gregorianMonthsArray.getString(i);

            JSONArray weekDaysArray = messages.getJSONArray("WeekDays");
            for (int i = 0; i < 7; ++i)
                weekDays[i] = weekDaysArray.getString(i);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }



    public List<DayEntity> getDays(int offset) {
        List<DayEntity> days = new ArrayList<>();
        PersianDate persianDate = getToday();
        int month = persianDate.getMonth() - offset;
        month -= 1;
        int year = persianDate.getYear();

        year = year + (month / 12);
        month = month % 12;
        if (month < 0) {
            year -= 1;
            month += 12;
        }
        month += 1;
        persianDate.setMonth(month);
        persianDate.setYear(year);
        persianDate.setDayOfMonth(1);
        if(!Setting.isEnLang()) {
            int dayOfWeek = DateConverter.persianToCivil(persianDate).getDayOfWeek() % 7;

            try {
                PersianDate today = getToday();
                for (int i = 1; i <= 31; i++) {
                    persianDate.setDayOfMonth(i);

                    DayEntity dayEntity = new DayEntity();
                    dayEntity.setNum(formatNumber(i));
                    dayEntity.setDayOfWeek(dayOfWeek);

                    if (dayOfWeek == 6 || !TextUtils.isEmpty(getEventsTitle(persianDate, true))) {
                        dayEntity.setHoliday(true);
                    }

              /*  if (getEvents(persianDate).size() > 0) {
                    dayEntity.setEvent(true);
                }*/

                    dayEntity.setPersianDate(persianDate.clone());

                    if (persianDate.equals(today)) {
                        dayEntity.setToday(true);
                    }

                    days.add(dayEntity);
                    dayOfWeek++;
                    if (dayOfWeek == 7) {
                        dayOfWeek = 0;
                    }
                }
            } catch (DayOutOfRangeException e) {
                // okay, it was expected
            }
        }else {
            //  int dayOfWeek = DateConverter.persianToCivil(persianDate).getDayOfWeek() % 7;
            int dayOfWeek = persianDate.getDayOfWeek() % 7;
            int maxDay;
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month-1, 1);
            maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            Log.i("maxDayss" , month+ ":"+maxDay);


            try {
                PersianDate today = getToday();
                for (int i = 0 ;i < maxDay; i++) {
                    persianDate.setDayOfMonth(i+1);
                    Log.i("maxDayss" , i+ ":"+maxDay);
                    DayEntity dayEntity = new DayEntity();
                    dayEntity.setNum(String.valueOf(i+1));
                    dayEntity.setDayOfWeek(dayOfWeek-1);

                    dayEntity.setPersianDate(persianDate.clone());

                    if (persianDate.equals(today)) {
                        dayEntity.setToday(true);
                    }

                    days.add(dayEntity);
                    dayOfWeek++;
                    if (dayOfWeek == 7) {
                        dayOfWeek = 0;
                    }
                }
            } catch (DayOutOfRangeException e) {
                Log.i("maxDayss" , e.getMessage());
            }
        }

        return days;
    }

}

