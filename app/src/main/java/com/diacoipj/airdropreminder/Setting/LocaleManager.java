package com.diacoipj.airdropreminder.Setting;

//public class LocaleManager {
//    public static void setLocale(Context c) {
//        setNewLocale(c, getLanguage(c));
//    }
//
//    public static void setNewLocale(Context c, String language) {
//        persistLanguage(c, language);
//        updateResources(c, language);
//    }
//
//    public static String getLanguage(Context c) { ... }
//
//    private static void persistLanguage(Context c, String language) { ... }
//
//    private static void updateResources(Context context, String language) {
//        Locale locale = new Locale(language);
//        Locale.setDefault(locale);
//
//        Resources res = context.getResources();
//        Configuration config = new Configuration(res.getConfiguration());
//        config.locale = locale;
//        res.updateConfiguration(config, res.getDisplayMetrics());
//    }
//}
