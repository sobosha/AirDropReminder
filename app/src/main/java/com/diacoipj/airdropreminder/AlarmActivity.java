package com.diacoipj.airdropreminder;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mSoundManager;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

public class AlarmActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        setThemeAndProperties();
        context = AlarmActivity.this;
        aboveLockScreen();

        onSetData();

        findViewById(R.id.btnOkk).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            onBackPressed();
        });
    }

    public void setThemeAndProperties() {
        Setting.setNavigationColor(R.color.statusColor);
    }

    int drawable = R.drawable.zang;
    String emoji = "" ;
    public  void onSetData(){
            drawable = R.drawable.zang;
            new Setting().setTypeFace(findViewById(R.id.txtTimeDays));
            ((TextView) findViewById(R.id.textTitle)).setText(getIntent().getStringExtra("title"));
            ((TextView) findViewById(R.id.textDesc)).setText(getIntent().getStringExtra("description"));
            ((TextView) findViewById(R.id.txtTimeDays)).setText(getIntent().getStringExtra("link"));
        ((TextView) findViewById(R.id.txtTimeDays)).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("link")));
            startActivity(intent);
        });
            setNotifSound();
            ((ImageView)findViewById(R.id.imgIcon)).setImageResource(drawable);



    }

    Context context ;
    ReminderData data ;

    @Override
    public void onBackPressed() {
        finish();
    }

    public void setNotifSound()
    {
        MediaPlayer mediaPlayer ;
        mSoundManager.getGlobal().DingAlarm();
        mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.ding);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }
    public void aboveLockScreen() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }
}