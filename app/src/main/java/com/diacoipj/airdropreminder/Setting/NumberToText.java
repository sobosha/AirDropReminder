package com.diacoipj.airdropreminder.Setting;


import com.diacoipj.airdropreminder.MainActivity;

public class NumberToText {

    public static String Convert(int t) {
        int Val = t;
        if (Val < 20)
            return Yek(Val);
        else if (Val >= 20 && Val < 100)
            return Dah(Val);
        else if (Val  >= 100 && Val  < 1000 )
            return Sad(Val);
        else if (Val  >= 1000 && Val < 1000000 )
             return Hezar(Val);
        else
            return Melion(Val);
    }


    private static String Yek(int t)
    {
        String[] a = {"صفر", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه", "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نونزده"};
        return a[t];
    }

    private static String Dah(int t)
    {
        String[] b = {"صفر", "ده", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
        if(t % 10 == 0)
            return b[(t/10)];
        else
            return b[t/10] + " و " + Yek(t%10);
    }

    private static String Sad(int t)
    {
        String[] c = {"صفر", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"};
        if (t % 10 == 0 && t % 100 == 0)
            return c[t / 100];
        else if (t > ((t / 100) * 100) && t < ((t / 100) * 100 + 20))
            return c[t / 100] + " و " + Yek(t% 100);
        else
            return c[t / 100] + " و " + Dah(t % 100);
    }

    private static String Hezar(int t)
    {
        if(t % 1000 == 0)
            return Convert(t/1000) + " هزار";
        else
            return Convert(t/1000) + " هزار و " + Convert(t%1000);
    }

    private static String Melion(int t)
    {
        if(t % 1000000 == 0)
            return Convert(t/1000000) + " میلیون";
        else
            return Convert(t/1000000) + " میلیون و " + Convert(t%1000000);
    }

    public static String month(int t)
    {
        String[] a ;
        a = DateManager.MonthNames(MainActivity.getGlobal());
        return a[t];
    }
}
