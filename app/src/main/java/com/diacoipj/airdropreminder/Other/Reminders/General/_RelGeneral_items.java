package com.diacoipj.airdropreminder.Other.Reminders.General;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.AlarmManager.AlaramManager;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.Other.Reminders.General.General.AddReminderGeneral;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

public class _RelGeneral_items extends RelativeLayout {
    RelGeneralReminders relGeneralReminders;

    public _RelGeneral_items(Context context, RelGeneralReminders relGeneralReminders) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rel_greminder_item, this, true);
        this.relGeneralReminders = relGeneralReminders;

    }

    FragReminder parent;
    String[] settedTime;
    int settedHour, settedMin;
    public AdapterRGeneral adapter;
    int pos;

    public void onStart(ReminderData data, int pos, FragReminder parent, AdapterRGeneral adapter) {
        this.data = data;
        this.parent = parent;
        this.adapter = adapter;
        this.pos = pos;
        parent.setRelGeneral_items(this);
        settedTime = Setting.textSeprator(data.getAlertHour(), ":");
        settedHour = Integer.parseInt(settedTime[0]);
        settedMin = Integer.parseInt(settedTime[1]);
        setStyle(true);
        clicks();
        ((TextView) findViewById(R.id.txtDays)).setText(data.getText());
    }

    public void clicks() {
        if (data.getFlag() == 2) {
            findViewById(R.id.relDays).setOnClickListener(v -> ((SwitchCompat) findViewById(R.id.switchBtn)).setChecked(true));
        }
        ((SwitchCompat) findViewById(R.id.switchBtn)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!mLocalData.isFirstReminder(getContext())) {
                if (Build.VERSION.SDK_INT > 28 && !Settings.canDrawOverlays(getContext())) {
                    CheckRunActivityInBackground();
                } else {
                    mLocalData.setFirstReminder(getContext(), true);
                    MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ReminderDialog(parent, " تنطیم کردن یادآور باعث مصرف بیشتر باطری میشود ایا مایل ب ادامه هستید  "
                            , v -> {
                                showTimePicker(false);
                                MainActivity.getGlobal().HideMessageBox();
                            }, v -> {
                                ((SwitchCompat) findViewById(R.id.switchBtn)).setChecked(false);
                                MainActivity.getGlobal().HideMessageBox();
                            }, pos));
                }
                return;
            }


            if (!isChecked) {
                data.setFlag(2);
               // parent.timeSetSuccesfully(data);
                new AlaramManager(getContext(), settedHour, settedMin, data.getId(), isNotifEnable(),"","","").cancle();
                //parent.ReminderSaveInDB(data, data.getIsAirDrop(), true);
            } else {
                if (Build.VERSION.SDK_INT > 28 && !Settings.canDrawOverlays(getContext())) {
                    CheckRunActivityInBackground();
                } else
                    showTimePicker(false);
            }

            setStyle(false);
        });
        /*-----------------------------------------  active or deActive the sound ---------------------------*/
        // 1 no  - 2 yes
        findViewById(R.id.imgSound).setOnClickListener(view -> {
            mAnimation.PressClick(view);
            if (data.getSound() == 1) {
                data.setSound(2);
                ((ImageView) findViewById(R.id.imgSound)).setImageResource(R.drawable.sound);
            } else {
                data.setSound(1);
                ((ImageView) findViewById(R.id.imgSound)).setImageResource(R.drawable.sound_no);
            }
            //parent.ReminderSaveInDB(data, data.getIsAirDrop(), true);
        });

        /*-----------------------------------------  changeTime ---------------------------*/
        findViewById(R.id.LinTimes).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            showTimePicker(isNotifEnable());
        });
    }

    public void showTimePicker(boolean isNotif) {
        MainActivity.getGlobal().FinishRelStartRel(new AddReminderGeneral(getContext(), (hour, min) -> {
            MainActivity.getGlobal().HideMyRelDialog();
            data.setFlag(1);
            data.setAlertHour(hour + ":" + min);
            new AlaramManager(getContext(), hour, min, data.getId(), isNotif,"","","");
          //  parent.timeSetSuccesfully(data);
            //parent.ReminderSaveInDB(data, data.getIsAirDrop(), true);
            relGeneralReminders.adapterRGeneral.notifyItemChanged(pos);
        }));
    }

    public void CheckRunActivityInBackground() {
        String desc;
        if (!mLocalData.isFirstReminder(getContext())) {
            desc = MainActivity.getGlobal().getResources().getString(R.string.ReminderDesc1);
            mLocalData.setFirstReminder(getContext(), true);
        } else
            desc = "";


        MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ReminderDialog(parent, desc + MainActivity.getGlobal().getResources().getString(R.string.ReminderDesc2)
                , v -> {
                    // MainActivity.getGlobal().startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:com.diacoipj.airdropreminder")), 1112);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!Settings.canDrawOverlays(getContext())) {
                            ((SwitchCompat) findViewById(R.id.switchBtn)).setChecked(false);
                            int REQUEST_CODE = 101;
                            Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            myIntent.setData(Uri.parse("package:" + MainActivity.getGlobal().getPackageName()));
                            MainActivity.getGlobal().startActivityForResult(myIntent, REQUEST_CODE);
                        }
                    }
                    showTimePicker(false);
                    MainActivity.getGlobal().HideMessageBox();
                }, v -> {
                    showTimePicker(true);
                    MainActivity.getGlobal().HideMessageBox();
                }, pos));

    }

    public boolean isNotifEnable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(getContext()))
                return true;
            else
                return false;
        } else
            return false;
    }

    public ReminderData data;

    public void setStyle(boolean isSetCheckBtns) {
        ((TextView) findViewById(R.id.txtTimeDays)).setText(data.getAlertHour());
        findViewById(R.id.LinTimes).setBackground(Setting.setBackWithStroke(getContext(),
                Color.parseColor("#00000000"),
                Color.parseColor("#ffffff"), 100));
        findViewById(R.id.imgSound).setBackground(Setting.setBackWithStroke(getContext(),
                Color.parseColor("#00000000"),
                Color.parseColor("#ffffff"), 100));

        /*-------------------------- set visibilities  -----------------------------*/


        if (data.getFlag() == 1 || data.getFlag() == 2) {
            findViewById(R.id.txtLine).setVisibility(View.GONE);
            findViewById(R.id.imgPlusDay).setVisibility(View.GONE);
        } else {
            findViewById(R.id.txtLine).setVisibility(VISIBLE);
            findViewById(R.id.imgPlusDay).setVisibility(VISIBLE);
            findViewById(R.id.imgPlusDay).setBackground(Setting.setBackWithStroke(getContext(),
                    Color.parseColor("#9287ff"),
                    Color.parseColor("#9287ff"),
                    MainActivity.getGlobal().getResources().getDimension(R.dimen._100sdp)));
        }
        /*-------------------------- set bg and alpha -----------------------------*/
        if (data.getFlag() == 1) {
            findViewById(R.id.imgSound).setAlpha(1f);
            findViewById(R.id.LinTimes).setAlpha(1f);
            findViewById(R.id.imgSound).setVisibility(VISIBLE);
            findViewById(R.id.LinTimes).setVisibility(VISIBLE);
            if (isSetCheckBtns)
                ((SwitchCompat) findViewById(R.id.switchBtn)).setChecked(true);
            setColorStyle(findViewById(R.id.relDays), "#" + data.getBackColor(), R.dimen._15sdp);
        } else if (data.getFlag() == 3) {
            findViewById(R.id.imgSound).setAlpha(0.6f);
            findViewById(R.id.LinTimes).setAlpha(0.6f);
            findViewById(R.id.imgSound).setVisibility(VISIBLE);
            findViewById(R.id.LinTimes).setVisibility(VISIBLE);
            if (isSetCheckBtns)
                ((SwitchCompat) findViewById(R.id.switchBtn)).setChecked(true);
            setColorStyle(findViewById(R.id.relDays), "#40" + data.getBackColor(), R.dimen._15sdp);
        } else {
            findViewById(R.id.imgSound).setVisibility(GONE);
            findViewById(R.id.LinTimes).setVisibility(GONE);
            if (isSetCheckBtns)
                ((SwitchCompat) findViewById(R.id.switchBtn)).setChecked(false);
            setColorStyle(findViewById(R.id.relDays), "#70253858", R.dimen._15sdp);
        }

        if (data.getSound() == 1) {
            ((ImageView) findViewById(R.id.imgSound)).setImageResource(R.drawable.sound_no);
        } else ((ImageView) findViewById(R.id.imgSound)).setImageResource(R.drawable.sound);
    }

    public void setColorStyle(View view, String color1, int rad) {
        view.setBackground(new Setting().setBackColor(Color.parseColor(color1), Color.parseColor(color1), MainActivity.getGlobal().getResources().getDimension(rad)));
    }
}
