package com.diacoipj.airdropreminder.Setting;

import androidx.appcompat.app.AppCompatActivity;

import com.diacoipj.airdropreminder.R;

public class AppFontManager {

    private AppCompatActivity appCompatActivity;

    public static final String dastnevis = "dastnevis";
    public static final String negar = "negar";
    public static final String dana = "dana";



    public AppFontManager(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void setFont(String fontName){

        switch (fontName){


            case dastnevis:{
                appCompatActivity.getTheme().applyStyle(R.style.dastnevis, true);
                break;
            }

            case negar:{
                appCompatActivity.getTheme().applyStyle(R.style.negar, true);
                break;
            }

            case dana:{
                appCompatActivity.getTheme().applyStyle(!Setting.isEnLang()?R.style.dana:R.style.poppins, true);
                break;
            }

        }
    }
}