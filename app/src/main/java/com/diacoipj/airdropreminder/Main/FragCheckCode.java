package com.diacoipj.airdropreminder.Main;

import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomFragment;
import com.diacoipj.airdropreminder.Setting.TimerEvent;
import com.diacoipj.airdropreminder.Setting.mAnimation;

import java.util.concurrent.TimeUnit;

public class FragCheckCode extends CustomFragment {
    EditText code1,code2,code3,code4;
    TextView Timer,Finish,Cancel;
    @Override
    public int layout() {
        return R.layout.frag_checkcode;
    }

    @Override
    public void onCreateMyView() {
        setView();
        timerEvent = new TimerEvent() {
            @Override
            public void onTick(String time) {
                Timer.setText(time);
            }
            @Override
            public void onFinish() {
                Timer.setClickable(true);
                Timer.setText("resend");
                parent.findViewById(R.id.txtTimeDes).setOnClickListener(view -> {
                    mAnimation.PressClick(view);
                    //setMobile(phone);
                });
            }
        };
        Finish.setOnClickListener(v -> {
            if(Check()) {
                handler.removeCallbacksAndMessages(null);
                MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());
            }
        });
        Cancel.setOnClickListener(v -> {
            mBackPressed();
        });
        ShowTimer(120);
    }

    private void setView() {
        code1=parent.findViewById(R.id.Code1);
        code2=parent.findViewById(R.id.Code2);
        code3=parent.findViewById(R.id.Code3);
        code4=parent.findViewById(R.id.Code4);
        Timer=parent.findViewById(R.id.TextViewTimer);
        Finish=parent.findViewById(R.id.Finish);
        Cancel=parent.findViewById(R.id.Cancel);

    }

    public static String GetTimerString(long sec) {
        sec = sec * 1000;
        String hour = "";

        if (TimeUnit.MILLISECONDS.toHours(sec) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(sec)) > 0)
            hour = TimeUnit.MILLISECONDS.toHours(sec) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(sec)) + ":";

        String min = "";
        if (TimeUnit.MILLISECONDS.toMinutes(sec) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(sec)) > 0)
            min = (TimeUnit.MILLISECONDS.toMinutes(sec) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(sec))) + ":";

        String secend = TimeUnit.MILLISECONDS.toSeconds(sec) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sec)) + "";

        return hour + min + secend;
    }

    long TIME ;
    TimerEvent timerEvent ;
    final Handler handler = new Handler();
    public void ShowTimer(long sec) {
        TIME = sec;

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (TIME > 0) {
                    timerEvent.onTick(GetTimerString(TIME));
                    TIME--;
                    handler.postDelayed(this, 1000);
                } else
                    timerEvent.onFinish();
            }
        });
    }

    public boolean Check(){
        if(code1.getText().toString().equals("") || code2.getText().toString().equals("") || code3.getText().toString().equals("") || code4.getText().toString().equals("")) {
            mAnimation.Viberation(parent.findViewById(R.id.LinCode));
            return false;
        }
        return true;
    }

    @Override
    public void mBackPressed() {
        super.mBackPressed();
        MainActivity.getGlobal().FinishFragStartFrag(new FragLogin());

    }
}
