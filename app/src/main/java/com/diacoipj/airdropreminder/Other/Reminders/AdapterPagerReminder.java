package com.diacoipj.airdropreminder.Other.Reminders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.List;

public class AdapterPagerReminder extends PagerAdapter {
    Context context;
    ReminderAirDrop reminderAirDrop;
    ReminderIDo reminderIDo;
    FragReminder fragReminder;
    List<ReminderData> customData;//AIRDROP
    List<ReminderData> generalData;//IDO
    public AdapterPagerReminder(Context context,FragReminder fragReminder , List<ReminderData> reminderData ,  List<ReminderData> generalData) {
        this.context = context;
        this.fragReminder = fragReminder;
        this.customData = reminderData;
        this.generalData = generalData;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        switch (position) {
            case 0: {
                reminderIDo = new ReminderIDo(context , generalData);
                container.addView(reminderIDo);
                reminderIDo.onStart(fragReminder);
                return reminderIDo;

            }
            default: {
                reminderAirDrop = new ReminderAirDrop(context , customData);
                container.addView(reminderAirDrop);
                reminderAirDrop.onStart(fragReminder);
                return reminderAirDrop;
            }
        }
    }



    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
