package com.diacoipj.airdropreminder.Other.Reminders;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.diacoipj.airdropreminder.Main.FragBuyPermium;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.Custome.AdapterReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomRel;
import com.diacoipj.airdropreminder.Setting.MyRecyclerScroll;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder.Setting.nValue;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReminderIDo extends CustomRel {
    List<ReminderData> listReminder;
    FragReminder reminder ;
    public ReminderIDo(Context context, List<ReminderData> listReminder) {
        super(context, R.layout.frag_reminder_ido);
        this.listReminder=listReminder;
        setStyle();
    }

    public void setStyle()
    {
        Collections.sort(listReminder);
        new Setting().setTypeFace(findViewById(R.id.txtReminder));
        Setting.setIconTinitColor(getContext(),((ImageView)findViewById(R.id.imgClock)),R.color.colorWhite);
        findViewById(R.id.newReminder).setBackground(new Setting().setBackStroke(0, Color.parseColor("#ffbb33"),Color.parseColor("#ffbb33"), MainActivity.getGlobal().getResources().getDimension(R.dimen._10sdp)));

    }

    public void onStart(FragReminder fragReminder) {
        reminder = fragReminder ;

        Setting.SetVerticalRecyclerAdapter(findViewById(R.id.RecyclerView),new AdapterReminder(listReminder , fragReminder));

        findViewById(R.id.newReminder).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            MainActivity.getGlobal().TypeOfReminder="ido";
            if(reminder.totalSize==reminder.freeCount && reminder.isVip==-1) {
                MainActivity.getGlobal().FinishFragStartFrag(new FragBuyPermium());
            }else {
                if (!mLocalData.isFirstReminder(getContext())) {
                    if (Build.VERSION.SDK_INT > 28 && !Settings.canDrawOverlays(getContext())) {
                        new Setting().CheckRunActivityInBackground(getContext());
                    } else {
                        MainActivity.getGlobal().FinishFragStartFrag(new FragGetReminderInfo().getInsertInstance("ido"));
                    }
                    mLocalData.setFirstReminder(getContext(), true);
                    return;
                }
                if (Build.VERSION.SDK_INT > 28 && !Settings.canDrawOverlays(getContext())) {
                    new Setting().CheckRunActivityInBackground(getContext());
                } else {
                    nValue.getGlobal().setDaysReminder(new ArrayList<>());
                    MainActivity.getGlobal().FinishFragStartFrag(new FragGetReminderInfo().getInsertInstance("ido"));
                }

            }

        });

    }


}
