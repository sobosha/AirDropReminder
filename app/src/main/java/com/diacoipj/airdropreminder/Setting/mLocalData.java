package com.diacoipj.airdropreminder.Setting;

import android.content.Context;
import android.content.SharedPreferences;



public class mLocalData {




    public static void setLogInstall(Context context, boolean sync) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putBoolean("install", sync);
        editor.apply();
    }


    public static boolean getLogInstall(Context context) {

        SharedPreferences prefs =
                context!=null ?context.getSharedPreferences("MyDB", Context.MODE_PRIVATE)
                : nValue.getGlobal().getContext().getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getBoolean("install", false);
    }


    /*------------------------------------------- token ------------------------------------------------------*/

    public static void SetToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences prefs =
                context!=null ?context.getSharedPreferences("MyDB", Context.MODE_PRIVATE) :
                        nValue.getGlobal().getContext().getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getString("token", "");
         /*return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6IjciLCJleHBpcmUiOjE1Njc3NTYyMzN9." +
                 "SBfrq85reHAhkhUvkZQ60H9GkOalua6br8acFuFyELCKDXCrvlTFY9pWsOJo6KEMUJv_ArXnJ-iNTwtD_g1aOg";*/
    }

    /*-------------------------- mobile -----------------------*/
    public static void setEmail(Context context, String mobile) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putString("email", mobile);
        editor.apply();
    }

    public static String getEmail(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getString("email", "");

    }


    /*--------------------------------------------------*/

    public static void SetCurrentDay(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putString("curDay", token);
        editor.apply();
    }

    public static String getCurrentDay(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getString("curDay", "");
    }
    public static void setName(Context context, String name) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putString("namee", name);
        editor.apply();
    }

    public static String getName(Context context) {
        if(context==null)
            context=nValue.getGlobal().getContext();
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getString("namee", "");
    }


    /*------------- push FCM ---------------------------------*/
    public static void setPush(Context context, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putBoolean("pushe", value);
        editor.apply();
    }

    public static boolean getPush(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getBoolean("pushe", false);
    }


    /*------------------------------- check first time setting reminder ---------------------*/

    public static Boolean isFirstReminder(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getBoolean("firstReminder", false);
    }

    public static void setFirstReminder(Context context, boolean fisrt) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putBoolean("firstReminder", fisrt);
        editor.apply();
    }

    public static Boolean isBuyPermium(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getBoolean("permiumtext", false);
    }

    public static void SetBuyPermium(Context context, boolean fisrt) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putBoolean("permiumtext", fisrt);
        editor.apply();
    }

    public static void setLanguage(Context context,String lang)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE).edit();
        editor.putString("transLang", lang);
        editor.apply();
    }

    public static String getLanguage(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("MyDB", Context.MODE_PRIVATE);
        return prefs.getString("transLang" , "en");
    }

}
