package com.diacoipj.airdropreminder._OfflineData;



public class OffLineData {


    public OffLineData() {
    }


    public String getDifficult (String tag) {
        String description;
        switch (tag) {
            case "xia": {
                description = ".1\n" +
                        "ابتدا به تنظیمات گوشی برو (setting)" + "\n" +
                        "قسمت برنامه\u200Cها رو انتخاب کن (app)" + "\n" +
                        "حالا وارد مدیریت برنامه\u200Cها شو (manage app)" + "\n" +
                        "لیوم رو انتخاب کن" + "\n" +
                        "1) تیک شروع خودکار رو فعال کن (auto start)" + "\n" +
                        "2) به قسمت سایر مجوز ها برو (other permission)" + "\n" +
                        "آغاز در پس زمینه رو انتخاب کن (start in background)" + "\n" +
                        "بعد تأیید و قبولش کن (accept)" + "\n\n"

                        + ".2\n"
                        + "باطری و عملکرد رو انتخاب کن (battery & performance)" + "\n" +
                        "محافظ باتری رو خاموش کن (battery saver)" +"\n" +
                        "باتری و عملکرد رو انتخاب کن (battery & performance)" +"\n" +
                        "محافظ باتری برنامه رو انتخاب کن (app battery saver)" +"\n" +
                        "لیوم رو بدون محدودیت کن (unlimited)" + "\n\n"

                        + ".3\n" +
                        "ابتدا به تنظیمات گوشی برو (setting)" + "\n" +
                        "قسمت برنامه\u200Cها رو انتخاب کن (app)" +"\n" +
                        "حالا وارد مدیریت برنامه\u200Cها شو (manage app)" +"\n" +
                        "بعد سایر مجوزها گزینه همه موارد رو قبول کن (other permissions)";
                break;
            }
            case "hua": {
                description =
                        ".1\n" +
                                "ابتدا به تنظیمات گوشی برو (setting)" +"\n" +
                                "حالا قسمت باتری رو انتخاب کن (battery)" +"\n" +
                                "تو این قسمت راه اندازی رو انتخاب کن (app lunch)" + "\n" +
                                "حالا لیوم رو غیرمجاز کن و بذار (run in background) فعال باشه" + "\n\n"
                                + ".2\n"
                                +"به تنظیمات برو (setting)" + "\n" +
                                "قسمت باتری رو انتخاب کن (battery)" +"\n" +
                                "حالت ذخیره نیرو رو خاموش کن (power saving mode)" + "\n\n"
                                + ".3\n" +
                                "به تنظیمات برو (setting)" + "\n" +
                                "قسمت باتری رو انتخاب کن (battery)" + "\n" +
                                "حالت ذخیره نیرو بیشتر رو خاموش کن (ultra power saving mode)" + "\n\n"
                                + ".4\n"
                                + "ابتدا به تنظیمات گوشی برو (setting)" + "\n" +
                                "قسمت باتری رو انتخاب کن (battery)" +"\n" +
                                "حالا قسمت بهینه ساز باتری رو انتخاب کن (optimize battery)" +"\n" +
                                "بعد لیوم رو غیر مجاز کن";
                break;
            }
            case "sam": {
                description = ".1\n" +
                        "ابتدا به تنظیمات گوشی برو (setting)" + "\n" +
                        "حالا قسمت مراقب دستگاه رو انتخاب کن (device care)" +"\n" +
                        "روی سه نقطه بالای صفحه بزن" +"\n" +
                        "پیشرفته رو انتخاب کن (advanced)" +"\n" +
                        "1) بهینه سازی خودکار رو انتخاب و غیر فعال کن (auto optimizition)" +"\n" +
                        "2) بهینه کردن تنظیمات رو غیرفعال کن (optimize setting)" + "\n\n"

                        + ".2\n" +
                        "ابتدا به تنظیمات گوشی برو (setting)" +"\n" +
                        "حالا قسمت مراقب دستگاه رو انتخاب کن (device care)" +"\n" +
                        "باتری رو انتخاب کن (battery)" +"\n" +
                        "حالت نیرو رو انتخاب کن (power mode)" +"\n" +
                        "1) حالت بهینه شده رو خاموش کن (optimized)" +"\n" +
                        "2) ذخیره نیرو انطباقی رو خاموش کن (adaptive power saving)";

                break;
            }
            default: {
                description = ".1\n" +
                        "ابتدا به تنظیمات گوشی برو (setting)" +"\n" +
                        "حالا قسمت مراقب دستگاه رو انتخاب کن (device care)" +"\n" +
                        "بعد سه نقطه بالای صفحه رو انتخاب کن" +"\n" +
                        "قسمت (advanced) رو انتخاب کن" +"\n" +
                        "1) بهینه سازی خودکار رو خاموش کن (auto optimization)" +"\n" +
                        "2) بهینه کردن تنظیمات رو خاموش کن (optimize setting)"+ "\n\n"

                        + ".2\n"
                        + "ابتدا به تنظیمات گوشی برو (setting)" +"\n" +
                        "حالا قسمت مراقب دستگاه رو انتخاب کن (device care)" +"\n" +
                        "باتری رو انتخاب کن  (battery)" +"\n" +
                        "حالت نیرو رو انتخاب کن (power mod)" +"\n" +
                        "1) حالت بهینه شده رو خاموش کن (optimized)" +"\n" +
                        "2) ذخیره نیرو انطباقی رو خاموش کن (adaptive power saving)"+"\n\n"

                        + ".3\n" +
                        "ابتدا به تنظیمات گوشی برو (setting)" +"\n" +
                        "حالا قسمت مراقب دستگاه رو انتخاب کن (device care)" +"\n" +
                        "باتری رو انتخاب کن (battery)" +"\n" +
                        "مدیریت نیروی برنامه رو انتخاب کن (app power managment)" +"\n" +
                        "1) برنامه\u200Cهای در حال خواب لیوم رو غیر فعال کن (sleeping apps)" +"\n" +
                        "2) برنامه\u200Cهایی که به خواب نمی\u200Cروند لیوم را به قسمت افزودن برنامه اضافه کن (wont be to sleep apps that)" +"\n" +
                        "3) باتری تطبیقی رو خاموش کن (adaptive baterry)" + "\n\n" +
                        ".4\n"+
                        "ابتدا به تنظیمات گوشی برو (setting)"+"\n" +
                        "برنامه\u200Cها رو انتخاب کن (app)"+"\n" +
                        "لیوم رو انتخاب کن" +"\n"+
                        "باتری رو انتخاب کن (battery)"+"\n" +
                        "1) فعالیت در پس زمینه رو روشن کن"+"\n" +
                        "2) بهینه سازی رو انتخاب کن (optimize battery usage)" +"\n"+
                        "از قسمت نوار بالای صفحه همه برنامه\u200Cها رو انتخاب کن" +"\n"+
                        "لیوم رو غیر فعال کن";
            }

        }
        return description;
    }






}