
package com.diacoipj.airdropreminder;

import android.annotation.SuppressLint;

import com.diacoipj.airdropreminder.Other.Drawer.RelDrawer;
import com.diacoipj.airdropreminder.Setting.AppFontManager;
import com.diacoipj.airdropreminder.Setting.DateManager;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.text.InputFilter;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;

import com.diacoipj.airdropreminder.Setting.ApplicationLock;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomRel;
import com.diacoipj.airdropreminder.Setting.CustomSnackBar;
import com.diacoipj.airdropreminder.Setting.mSoundManager;
import com.diacoipj.airdropreminder.Setting.nValue;
import com.diacoipj.airdropreminder.Start.FragSplash;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mFragment;
import com.diacoipj.airdropreminder.Setting.mLocalData;

import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import co.ronash.pushe.Pushe;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.metrix.Metrix;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private static MainActivity global;

    public static MainActivity getGlobal() {
        return global;
    }

    public MainActivity() {
        global = this;
    }


    public String blockCharacterSet = "@";

    public String TypeOfReminder="ido";
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    String TAPSELL_KEY = "rflgasbbfbtlmdmgrlaestfpoeclmeqpblraqhashsafihrdqqnfbdmtgskkcrongiemrs";
    public String WalletAddress="",Viptxt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new AppFontManager(this).setFont("dana");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setThemeAndProperties();
        services();
        Presenter.get_global().OnCreate(this, "https://api.liom-app.ir/", "");
        mSoundManager.getGlobal().OnCreate(MainActivity.this);
        OpanApp();
        if (!new DateManager().getTodayDate(false, false, "-1").equals(mLocalData.getCurrentDay(getApplicationContext()))) {
                 isChange = true;
           /*       mLocalData.setUserSendData(this,false);
                  mLocalData.setChangeSocialInfo(this ,false);*/
    }
        else
            isChange = false;
        //onRefreshGeneralRemindersList();

    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    public  void  OpanApp(){
        FinishFragStartFrag(new FragSplash());



    }
    public boolean isShowDrawer = false;
    public void showDrawer() {
        isShowDrawer = true;
        findViewById(R.id.relDrawer).setVisibility(VISIBLE);
        findViewById(R.id.imgBlackMain).setVisibility(VISIBLE);
        ((RelativeLayout) findViewById(R.id.relDrawer)).addView(new RelDrawer(this));

        findViewById(R.id.imgBlackMain).setOnClickListener(v -> hideDrawer());
    }

    public void hideDrawer() {
        findViewById(R.id.imgBlackMain).setVisibility(GONE);
        isShowDrawer = false;
        mAnimation.myTransInRightReturn(findViewById(R.id.relDrawer), 0, 500).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((RelativeLayout) findViewById(R.id.relDrawer)).removeAllViews();
                findViewById(R.id.relDrawer).setVisibility(GONE);
                findViewById(R.id.relDrawer).clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void services() {
        Pushe.initialize(this, true);
        Pushe.subscribe(this, "allnew");
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("allnew");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        Metrix.setPushToken(FirebaseInstanceId.getInstance().getToken());

    }

    /* -------------------------- methodes --------------------------*/

    public void setThemeAndProperties() {
        Setting.setNavigationColor(R.color.statusColor);
    }


    public List<ReminderData> generalData = new ArrayList<>();
    public List<ReminderData> customData = new ArrayList<>();
    boolean isChange = false;

    public void onRefreshGeneralRemindersList() {
        List<ReminderData> list=new ArrayList<>();
        list.addAll(generalData);
        list.addAll(customData);
        String timeData[];
        String date = new DateManager().getTodayDate( false, false,"");

        for (int i = 0; i < list.size(); i++) {
            if(new Setting().CompareTwoDateMiladi(date.replace("/","-"),list.get(i).getAlertDate().replace("/","-"))==1){
                if(generalData.contains(list.get(i))){
                    generalData.get(generalData.indexOf(list.get(i))).setFlag(3);
                }
                if(customData.contains(list.get(i))){
                    customData.get(customData.indexOf(list.get(i))).setFlag(3);
                }
                //new SaveInDB(this , "").ReminderSaveInDB(list.get(i), list.get(i).getType(), true);
            }
            if(new Setting().CompareTwoDateMiladi(date.replace("/","-"),list.get(i).getAlertDate().replace("/","-"))==0){
                String time=getTimeCurrent();
                int hour=Integer.parseInt(list.get(i).getAlertHour().split(":")[0]),currenthour=Integer.parseInt(time.split(":")[0]),min=Integer.parseInt(list.get(i).getAlertHour().split(":")[1]),currentmin=Integer.parseInt(time.split(":")[1]);
                if(hour>currenthour || (hour<=currenthour && min<currentmin)) {
                    if (generalData.contains(list.get(i))) {
                        generalData.get(generalData.indexOf(list.get(i))).setFlag(3);
                    }
                    if (customData.contains(list.get(i))) {
                        customData.get(customData.indexOf(list.get(i))).setFlag(3);
                    }
                    //new SaveInDB(this , "").ReminderSaveInDB(list.get(i), list.get(i).getType(), true);
                }
            }
        }


    }

    /*----------------------------- dialogs ----------------------------*/
    public boolean isMainDialogShow = false;
    String phone = "";
    String reminderText = "";
    public void setReminderText(String reminderText) {
        this.reminderText = reminderText;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public  boolean isLoginShow = false ;
    public String backReminder = "";

    public String getBackReminder() {
        return backReminder;
    }

    public void setBackReminder(String backReminder) {
        this.backReminder = backReminder;
    }



    CustomRel customRel ;
    public  boolean isRelShow ;
    public void FinishRelStartRel (CustomRel customRel) {
        isRelShow = true ;
        this.customRel = customRel ;
        ((RelativeLayout)findViewById(R.id.relMainDialogs)).removeAllViews();
        findViewById(R.id.relMainDialogs).setVisibility(VISIBLE);
        mAnimation.myFadeIn(findViewById(R.id.relMainDialogs) , 0 , 300);
        ((RelativeLayout)findViewById(R.id.relMainDialogs)).addView(customRel);
        findViewById(R.id.imgBlackMain).setVisibility(VISIBLE);
        findViewById(R.id.imgBlackMain).setOnClickListener(view -> HideMyRelDialog());
    }

    public void HideMyRelDialog () {
        isRelShow = false ;
        customRel = null ;
        findViewById(R.id.relMainDialogs).setVisibility(GONE);
        findViewById(R.id.imgBlackMain).setVisibility(GONE);
        Setting.OnAnimationEnd(mAnimation.myFadeOut(findViewById(R.id.relMainDialogs), 0, 300), () -> {
            ((RelativeLayout)findViewById(R.id.relMainDialogs)).removeAllViews();
            findViewById(R.id.relMainDialogs).clearAnimation();
        });
    }

    public void hideMainDialogs() {
        isMainDialogShow = false;
        isLoginShow = false;
        findViewById(R.id.relMainDialogs).setVisibility(GONE);
        findViewById(R.id.imgBlackMain).setVisibility(GONE);
        mAnimation.myTransToLeft(findViewById(R.id.relMainDialogs), 0, 500, 0, 2).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.relMainDialogs).clearAnimation();
                ((RelativeLayout) findViewById(R.id.relMainDialogs)).removeAllViews();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        findViewById(R.id.imgBlackMain).setOnClickListener(view -> hideMainDialogs());

    }

    public boolean isMessageBox = false;
    public void ShowMessageBox(RelMessageBox relMessageBox) {
        ((RelativeLayout)findViewById(R.id.relDialog)).removeAllViews();
        if(isMainDialogShow)
              MainActivity.getGlobal().hideMainDialogs();
        if (!isLoginShow) {
            isMessageBox = true;
            findViewById(R.id.relDialog).setVisibility(VISIBLE);
            ((RelativeLayout) findViewById(R.id.relDialog)).addView(relMessageBox);
            mAnimation.myFadeIn(findViewById(R.id.relDialog), 0, 300);
        }
    }


    public void HideMessageBox() {
        isMessageBox = false;
        mAnimation.myFadeOut(findViewById(R.id.relDialog), 0, 300).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((RelativeLayout) findViewById(R.id.relDialog)).removeAllViews();
                findViewById(R.id.relDialog).setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    boolean canShowSnack = true, showSnack;
    Handler handler;
    Runnable runnable;

    public void showSnackBar(String type, String desc, int duration) {
        if (canShowSnack) {
            showSnack = true;
            canShowSnack = false;
            findViewById(R.id.relSnackBar).setVisibility(VISIBLE);
            if (type.equals("accept") || type.equals("pending") || type.equals("reject") || type.equals("warning") || type.equals("normal")) {
                mAnimation.myTrans_ToTopSnack(findViewById(R.id.relSnackBar), 0, 500);
                ((RelativeLayout) findViewById(R.id.relSnackBar)).addView(new CustomSnackBar(this, type, desc));
                if (handler == null)
                    handler = new Handler(Looper.getMainLooper());
                runnable = () -> hideSnackBar(false);
                handler.postDelayed(runnable, duration);
            }else {
                this.type = desc;
                mAnimation.myTrans_ToBottom(findViewById(R.id.relSnackBar), 0, 1500);
                ((RelativeLayout) findViewById(R.id.relSnackBar)).addView(new CustomSnackBar(this, type, desc));
            }
        }

    }

    String type = "" ;
    public void hideSnackBar(boolean isTop ) {
        showSnack = false;
        if(isTop){
            mAnimation.myTrans_ToBottomBAck(findViewById(R.id.relSnackBar), 0, 1000).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ((RelativeLayout) findViewById(R.id.relSnackBar)).removeAllViews();
                    findViewById(R.id.relSnackBar).setVisibility(GONE);
                    findViewById(R.id.relSnackBar).clearAnimation();
                    if (handler != null)
                        handler.removeCallbacks(runnable);
                    canShowSnack = true;

                }
                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
        } else {
            mAnimation.myTrans_ToBottom(findViewById(R.id.relSnackBar), 0, 500, 0.15f).setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }
                @Override
                public void onAnimationEnd(Animation animation) {
                    ((RelativeLayout) findViewById(R.id.relSnackBar)).removeAllViews();
                    findViewById(R.id.relSnackBar).setVisibility(GONE);
                    findViewById(R.id.relSnackBar).clearAnimation();
                    if (handler != null)
                        handler.removeCallbacks(runnable);
                    canShowSnack = true;
                }
                @Override
                public void onAnimationRepeat(Animation animation) { }
            });
        }
    }


    /* ------------------------------------------------------------------------------------*/

    private mFragment currentFragment;
    public void FinishFragStartFrag(mFragment newFragment) {
        Presenter.get_global().cancelReq();
        currentFragment = newFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.fade_show, R.anim.fade_hide);
        ft.replace(R.id.relMaster, newFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }



    public void FinishFragStartFragCustomAnimation(mFragment newFragment, int startAnimation, int endAnimation) {
        Presenter.get_global().cancelReq();
        currentFragment = newFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(startAnimation, endAnimation);
        ft.replace(R.id.relMaster, newFragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    public void closeApp() {
//        MainActivity.getGlobal().sendTrackedData(nValue.getGlobal().getViewList());
        MainActivity.getGlobal().finish();
    }

    public void onSuperBackApp(mFragment backfragment) {
        currentFragment = backfragment;
        super.onBackPressed();
    }

    public mFragment getCurrentFragment() {
        return currentFragment;
    }


    @Override
    public void onBackPressed() {
        if (isRelShow && customRel !=null) {
            customRel.onBackPressed();
            return;
        }
        if (!nValue.getGlobal().isBlockBack())
        {
            if (isMainDialogShow) {
                hideMainDialogs();
                return;
            }
            if (isLoginShow) {
                return;
            }
            if (showSnack)
                hideSnackBar(true);
            if (isMessageBox) {
                HideMessageBox();
                return;
            }

            if (isMessageBox) {
                HideMessageBox();
                return;
            }
            if (isShowDrawer) {
                hideDrawer();
                return;
            }
        }
        currentFragment.mBackPressed();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.clear();
    }

    @Override
    public void applyOverrideConfiguration(final Configuration overrideConfiguration) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 25) {
            overrideConfiguration.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    /* ------------------------------------     ----------------------------------*/
    @Override
    protected void onDestroy() {
        // for prevent leaking memory
        customRel = null ;
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ApplicationLock.activityStopped();
    }

    @Override
    protected void onResume() {
        if (ApplicationLock.activityStarted()) {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            ApplicationLock.lock = false;

        }
        super.onResume();
    }

    /*-------------------------------------------------------------------------------*/

    boolean mStartedActivityFromFragment = false;

    @Override
    public void startActivityForResult(@SuppressLint("UnknownNullness") Intent intent, int requestCode) {
        if (!mStartedActivityFromFragment) {
            if (requestCode != -1) {
                checkForValidRequestCode(requestCode);
            }
        }
        super.startActivityForResult(intent, requestCode);
    }

    static void checkForValidRequestCode(int requestCode) {
        if ((requestCode & 0xffff0000) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public void blcokCharacter(EditText editText) {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        };
        editText.setFilters(new InputFilter[]{filter});
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        currentFragment.mRequestPermissionResult(requestCode , permissions , grantResults);
    }


    Bundle bundleData = new Bundle();
    public  void saveData(Bundle date){
        this.bundleData = date;
    }

    public  Bundle getData(){
            return this.bundleData ;
    }

    public String getTimeCurrent() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateToStr = format.format(today);
        String[] sp = Setting.textSeprator(dateToStr, ":");
        return sp[0] + ":" + sp[1];
    }


    String currentString = Setting.getVersionName();
    String[] separated = currentString.split(" ");
    public void showReview(mFragment fragment) {
        try {
                Uri uri = Uri.parse("market://details?id=" + MainActivity.getGlobal().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    fragment.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    fragment.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + MainActivity.getGlobal().getPackageName())));
                }

        } catch (Exception e) {
            String market = "";
            String text = Setting.getVersionName();
            String[] split = text.split(" ");
            MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(MainActivity.getGlobal()).ExitButton("error", "عملیات ناموفق \n لطفا ابتدا از نصب بودن نرم افزار " + market + " مطمئن شوید"));
        }
    }
}

