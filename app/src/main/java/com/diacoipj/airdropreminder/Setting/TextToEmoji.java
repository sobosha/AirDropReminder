package com.diacoipj.airdropreminder.Setting;

import java.util.ArrayList;
import java.util.List;

public class TextToEmoji
{


    public TextToEmoji() {
        createUnicode();
    }

    public String ConvertText(String text)
    {
        String a = "";
        String test[] =  text.split("_");
        if (test.length>0)
        {
            for (int i = 0; i < test.length; i++) {
                for (int j = 0; j < unicodeList.size(); j++) {
                    if (test[i].equals(unicodeList.get(j).getMainCode()))
                        a = a+unicodeList.get(j).getCode();
                }
            }
        }
        else {
            for (int j = 0; j < unicodeList.size(); j++) {
                if (text.equals(unicodeList.get(j).getMainCode()))
                    a = a+unicodeList.get(j).getCode();
            }
        }


        return a;
    }


    List<unicode> unicodeList = new ArrayList<>();
    public static class unicode
    {
        String code , mainCode;

        public unicode(String code ,String mainCode) {
            this.code = code;
            this.mainCode = mainCode ;
        }

        public String getCode() {
            return code;
        }

        public String getMainCode() {
            return mainCode;
        }
    }


    public void createUnicode()
    {

        unicodeList.add(new unicode("\uD83D\uDE00" , "U+1F600"));
        unicodeList.add(new unicode("\uD83D\uDE01" , "U+1F601"));
        unicodeList.add(new unicode("\uD83D\uDE0A" , "U+1F60A"));
        unicodeList.add(new unicode("\uD83D\uDE07" , "U+1F607"));
        unicodeList.add(new unicode("\uD83D\uDE02" , "U+1F602"));
        unicodeList.add(new unicode("\uD83D\uDE0D" , "U+1F60D"));
        unicodeList.add(new unicode("☺️" , "U+263A"));
        unicodeList.add(new unicode("\uD83D\uDE18" , "U+1F618"));
        unicodeList.add(new unicode("\uD83D\uDE34" , "U+1F634"));
        unicodeList.add(new unicode("\uD83D\uDE12" , "U+1F612"));
        unicodeList.add(new unicode("\uD83E\uDD73" , "U+1F973"));
        unicodeList.add(new unicode("\uD83D\uDE0E" , "U+1F60E"));
        unicodeList.add(new unicode("\uD83E\uDD13" , "U+1F913"));
        unicodeList.add(new unicode("\uD83D\uDE48" , "U+1F648"));
        unicodeList.add(new unicode("\uD83D\uDC8B" , "U+1F48B"));
        unicodeList.add(new unicode("❤️" , "U+2764"));
        unicodeList.add(new unicode("\uD83D\uDCA5" , "U+1F4A5"));
        unicodeList.add(new unicode("\uD83D\uDCAB" , "U+1F4AB"));
        unicodeList.add(new unicode("✌️" , "U+270C"));
        unicodeList.add(new unicode("\uD83D\uDCAA" , "U+1F4AA"));
        unicodeList.add(new unicode("\uD83D\uDC76" , "U+1F476"));

    }
}
