package com.diacoipj.airdropreminder;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Looper;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.diacoipj.airdropreminder.Setting.LocaleHelper;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder._Core.CrashRequest;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.respons.UserData;
import com.jaredrummler.android.device.DeviceName;




public class MyApp extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.setLocale(this,mLocalData.getLanguage(getAppContext()));
    }

    String TAPSELL_KEY = "rflgasbbfbtlmdmgrlaestfpoeclmeqpblraqhashsafihrdqqnfbdmtgskkcrongiemrs";
    String FullModell;
    UserData userData  = new UserData();
    String userInfo ="";
    private static Context context;
    @Override
    public void onCreate() {

        super.onCreate();
//        LocaleHelper.setLocale(this,"fa-rAF");

      //  Tapsell.initialize(this, TAPSELL_KEY);
        MyApp.context = getApplicationContext();
//        ViewPump.init(ViewPump.builder()
//                .addInterceptor(new CalligraphyInterceptor(
//                        new CalligraphyConfig.Builder()
//                                .setDefaultFontPath("fonts/dana_fa_num_bold.ttf")
//                                .setFontAttrId(R.attr.fontPath)
//                                .build()))
//                .build());





        DeviceName.with(this).request(new DeviceName.Callback() {

            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {

                String manufacturer = info.manufacturer;
                String name = info.marketName;
                String model = info.model;
                FullModell = manufacturer  + " " + name + " " + model;

            }
        });

  Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread paramThread, final Throwable paramThrowable) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        String str ;
                        if(MainActivity.getGlobal() != null && MainActivity.getGlobal().getCurrentFragment()!=null && MainActivity.getGlobal().getCurrentFragment().getClass()!=null)
                            str = MainActivity.getGlobal().getCurrentFragment().getClass().getName();
                        else str = "";
                        str = str.replace("com.diacotdj.liom","");
                        str=str.replace(".","_");



                        CrashRequest request;
                        try {

                            String classNames = "";
                            for(int i = 0;i < paramThrowable.getStackTrace().length; i++)
                            {
                                classNames = classNames +  paramThrowable.getStackTrace()[i].getLineNumber() + " " + paramThrowable.getStackTrace()[i].getClassName() +"\n";
                            }

                            request = new CrashRequest(paramThrowable.toString() +
                                    "\n" + userInfo+
                                    "\nClass Name :\n" + paramThrowable.getStackTrace()[0].getClassName() +

                                    "\nMethod Name :\n" + paramThrowable.getStackTrace()[0].getMethodName() +
                                    "\nLine Number :\n" + paramThrowable.getStackTrace()[0].getLineNumber(),
                                    "\nDevice Name :" + FullModell + "\nAndroid SDK Version :" + android.os.Build.VERSION.SDK_INT + "   "+"#Android : "+getAndroidVersion()+
                                            "\nMethods :\n" + classNames + "\nFragment :" + str
                                    , str,
                                    "" ,  "" ,0);//todo change to 0 when release
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            try {
                                request = new CrashRequest(paramThrowable.getMessage() ,"\nDevice Name :" + FullModell + "\n\nAndroid SDK Version :" + android.os.Build.VERSION.SDK_INT ,str,
                                        ""  , "" ,0); //todo change to 0 when release
                            }
                            catch (Exception e1)
                            {
                                e1.printStackTrace();
                                request = new CrashRequest("خطای نامشخص","خطای نامشخص",str,""   , "",0);//todo change to 0 when release
                            }
                        }
                       if(new Setting().isNetworkConnect()) {
                           Presenter.get_global().OnCreate(MyApp.this, "https://api.liom-app.ir/", Presenter.get_global().getSERVER_KEY());
                           Presenter.get_global().PostAction(new IView<String>() {
                               @Override
                               public void SendRequest() {

                               }

                               @Override
                               public void OnSucceed(String object) {
                                   int a = 0;
                               }

                               @Override
                               public void OnError(String error, int statusCode) {
                                   int a = 0;
                               }
                           }, "report", "crash", "", request, String.class);
                       }
                        Looper.loop();
                    }
                }).start();


                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                System.exit(0);

            }
        });

    }

    public static Context getAppContext() {
        return MyApp.context;
    }

    double androidVersion;
    public double getAndroidVersion()
    {
        switch (android.os.Build.VERSION.SDK_INT)
        {
            case 18:
                androidVersion = 4.3;
                break;
            case 19:
            case 20:
                androidVersion = 4.4;
                break;
            case 21:
                androidVersion = 5.0;
                break;
            case 22:
                androidVersion = 5.1;
                break;
            case 23:
                androidVersion = 6.0;
                break;
            case 24:
                androidVersion = 7.0;
                break;
            case 25:
                androidVersion = 7.1;
                break;
            case 26:
                androidVersion = 8.0;
                break;
            case 27:
                androidVersion = 8.1;
                break;
            case 28:
                androidVersion = 9.0;
                break;
            case 29:
                androidVersion = 10.0;
                break;
            case 30:
                androidVersion = 11.0;
                break;
        }
        return androidVersion;
    }






}

