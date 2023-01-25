package com.diacoipj.airdropreminder.Setting;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.diacoipj.airdropreminder.R;

public class mSoundManager {

    private static mSoundManager global;

    public static mSoundManager getGlobal() {
        if (global != null)
            return global;
        else
            return new mSoundManager();
    }

    public mSoundManager() {
        global = this;
    }

    /*------------  default alarm  ------------*/
    private SoundPool soundPoolDefault = null;
    private int defaultId;
    String type="";

    public void DefaultAlarm(String type) {
        this.type = type;
        soundPoolDefault.play(defaultId, 1, 1, 0, 0, 1);
    }

    /*------------  Ballon alarm  ------------*/
    private SoundPool soundPoolBallon = null;
    private int ballonId;

    public void BallonAlarm() {
        soundPoolBallon.play(ballonId, 1, 1, 0, 0, 1);
    }

    /*------------  BreakAlarm  ------------*/
    private SoundPool soundPoolBreak = null;
    private int breakId;

    public void BreakAlarm() {
        soundPoolBreak.play(breakId, 1, 1, 0, 0, 1);
    }

    /*------------  HandAlarm  ------------*/
    private SoundPool soundPoolHand = null;
    private int handId;

    public void HandAlarm() {
        soundPoolHand.play(handId, 1, 1, 0, 0, 1);
    }

    /*------------  CheerAlarm  ------------*/
    private SoundPool soundPoolCheer = null;
    private int cheerId;

    public void CheerAlarm() {
        soundPoolCheer.play(cheerId, 1, 1, 0, 0, 1);
    }

    /*------------  TickGear  ------------*/
    private SoundPool soundPoolTick = null;
    private int TickId;

    public void TickAlarm() {
        soundPoolTick.play(TickId, 1, 1, 0, 0, 1);
    }


    /*------------  ClickGear  ------------*/
    private SoundPool soundPoolClick = null;
    private int ClickId;

    public void ClickAlarm() {
        soundPoolClick.play(ClickId, 1, 1, 0, 0, 1);
    }

    /*------------  TimerAlarm  ------------*/
    private SoundPool soundPoolTimer = null;
    private int TimerId;

    public void TimerAlarm() {
        soundPoolTimer.play(TimerId, 1, 1, 0, 0, 1);
    }

    /*------------  DingAlarm  ------------*/
    private SoundPool soundPoolDing = null;
    private int DingId;
    public void DingAlarm() {
        soundPoolDing.play(DingId, 1, 1, 0, 0, 1);
    }

    /*------------  BreathIn  ------------*/
    private SoundPool soundPoolBrathIn = null;
    private int BrathInId;
    public void BrathIn() {
        soundPoolBrathIn.play(BrathInId, 1, 1, 0, 0, 1);
    }
    /*------------  BreathOut  ------------*/
    private SoundPool soundPoolBrathOut = null;
    private int BrathOutId;
    public void BrathOut() {
        soundPoolBrathOut.play(BrathOutId, 1, 1, 0, 0, 1);
    }

//    --------------------------------- INIT NOTIF SOUNDS ---------------------------------
    /*------------  SportAlarm  ------------*/
    private SoundPool soundPoolSport =null;
    private int sportId;
    public void sportAlarm(){
        soundPoolSport.play(sportId,1,1,0,0,1);
    }
    /*------------  FruitAlarm  ------------*/
    private SoundPool soundPoolFruit =null;
    private int fruitId;
    public void fruitAlarm(){
        soundPoolFruit.play(fruitId,1,1,0,0,1);
    }
    /*------------  SleepAlarm  ------------*/
    private SoundPool soundPoolSleep =null;
    private int sleepId;
    public void sleepAlarm(){
        soundPoolSleep.play(sleepId,1,1,0,0,1);
    }
    /*------------  WaterAlarm  ------------*/
    private SoundPool soundPoolWater =null;
    private int waterId;
    public void waterAlarm(){
        soundPoolWater.play(waterId,1,1,0,0,1);
    }
    /*------------  BookAlarm  ------------*/
    private SoundPool soundPoolBook =null;
    private int bookId;
    public void bookAlarm(){
        soundPoolBook.play(bookId,1,1,0,0,1);
    }
    /*------------  MobileAlarm  ------------*/
    private SoundPool soundPoolMobile =null;
    private int mobiletId;
    public void mobileAlarm(){
        soundPoolMobile.play(mobiletId,1,1,0,0,1);
    }
    /*------------  DrugAlarm  ------------*/
    private SoundPool soundPoolDrug =null;
    private int drugId;
    public void drugAlarm(){
        soundPoolDrug.play(drugId,1,1,0,0,1);
    }


    /*----------------------------------------------------------------*/
    public void OnCreate(Context context) {
        int MAX_STREAMS = 20;
        int REPEAT = 0;


        /*   ------------------- default alarm ---------------------*/
        soundPoolDefault = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDefault.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                soundPoolDefault = soundPool;
                defaultId = soundId;
            }
        });
        soundPoolDefault.load(context, R.raw.coin, 1);




        /*   ------------------- default alarm ---------------------*/
        soundPoolBallon = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolBallon.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                soundPoolBallon = soundPool;
                ballonId = soundId;
            }
        });
        soundPoolBallon.load(context, R.raw.full_balloon1, 1);


        /*   ------------------- break alarm ---------------------*/
        soundPoolBreak = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolBreak.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                soundPoolBreak = soundPool;
                breakId = soundId;
            }
        });
        soundPoolBreak.load(context, R.raw.bumb, 1);

        /*   ------------------- hand alarm ---------------------*/
        soundPoolHand = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolHand.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                soundPoolHand = soundPool;
                handId = soundId;
            }
        });
        soundPoolHand.load(context, R.raw.hand, 1);



        /*   ------------------- cheer alarm ---------------------*/
        soundPoolCheer = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolCheer.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolCheer = soundPool;
            cheerId = soundId;
        });
        soundPoolCheer.load(context, R.raw.bulb_break, 1);

        /*   ------------------- tick alarm ---------------------*/
        soundPoolTick = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolTick.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolTick = soundPool;
            TickId = soundId;
        });
        soundPoolTick.load(context, R.raw.tick, 1);


        /*   ------------------- Click alarm ---------------------*/
        soundPoolClick = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolClick.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolClick = soundPool;
            ClickId = soundId;
        });
        soundPoolClick.load(context, R.raw.voice_light, 1);


        /*   ------------------- Timer alarm ---------------------*/
        soundPoolTimer = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolTimer.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolTimer = soundPool;
            TimerId = soundId;
        });
        soundPoolTimer.load(context, R.raw.timer, 1);



        /*   ------------------- Ding alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.ding, 1);


// -------------------------------------------   NOTIF ALARMS -------------------------------------------

        /*   ------------------- sport alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.ding, 1);

        /*   ------------------- fruit alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.notif_fruit, 1);

        /*   ------------------- sleep alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.notif_sleep, 1);

        /*   ------------------- water alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.notif_water, 1);

        /*   ------------------- book alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.notif_book, 1);

        /*   ------------------- mobile alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.ding, 1);

        /*   ------------------- drug alarm ---------------------*/
        soundPoolDing = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDing.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDing = soundPool;
            DingId = soundId;
        });
        soundPoolDing.load(context, R.raw.ding, 1);


//        ======================================= NOTIFications ======================================
        /*   ------------------- sport alarm ---------------------*/
        soundPoolSport = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolSport.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolSport = soundPool;
            sportId = soundId;
        });
        soundPoolSport.load(context, R.raw.ding, 1);

        /*   ------------------- Fruit alarm ---------------------*/
        soundPoolFruit = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolFruit.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolFruit = soundPool;
            fruitId = soundId;
        });
        soundPoolFruit.load(context, R.raw.ding, 1);

        /*   ------------------- sleep alarm ---------------------*/
        soundPoolSleep = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolSleep.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolSleep = soundPool;
            sleepId = soundId;
        });
        soundPoolSleep.load(context, R.raw.ding, 1);

        /*   ------------------- water alarm ---------------------*/
        soundPoolWater = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolWater.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolWater = soundPool;
            waterId = soundId;
        });
        soundPoolWater.load(context, R.raw.ding, 1);

        /*   ------------------- book alarm ---------------------*/
        soundPoolBook = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolBook.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolBook = soundPool;
            bookId = soundId;
        });
        soundPoolBook.load(context, R.raw.ding, 1);

        /*   ------------------- mobile alarm ---------------------*/
        soundPoolMobile = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolMobile.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolMobile = soundPool;
            mobiletId = soundId;
        });
        soundPoolMobile.load(context, R.raw.ding, 1);

        /*   ------------------- drug alarm ---------------------*/
        soundPoolDrug = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolDrug.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolDrug = soundPool;
            drugId = soundId;
        });
        soundPoolDrug.load(context, R.raw.ding, 1);
/* -----------------------صدای نفس کشیدن---------------------------*/
        soundPoolBrathIn = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolBrathIn.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolBrathIn = soundPool;
            BrathInId = soundId;
        });
       // soundPoolBrathIn.load(context, R.raw.breath_in, 1);

        /* -----------------------صدای نفس کشیدن---------------------------*/
        soundPoolBrathOut = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, REPEAT);
        soundPoolBrathOut.setOnLoadCompleteListener((soundPool, soundId, status) -> {
            soundPoolBrathOut = soundPool;
            BrathOutId = soundId;
        });
     //   soundPoolBrathOut.load(context, R.raw.breath_out, 1);

    }
}
