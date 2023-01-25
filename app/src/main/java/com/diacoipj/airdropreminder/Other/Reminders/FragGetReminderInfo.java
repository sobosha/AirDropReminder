package com.diacoipj.airdropreminder.Other.Reminders;

import static android.view.View.VISIBLE;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.Main.ResponseObject;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.AlarmManager.AlaramManager;
import com.diacoipj.airdropreminder.Other.Reminders.WalletTypes.WAdapter;
import com.diacoipj.airdropreminder.Other.Reminders.WalletTypes.WalletTypeList;
import com.diacoipj.airdropreminder.Other.Reminders.WalletTypes.WalletsTypes;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomFragment;
import com.diacoipj.airdropreminder.Setting.DateManager;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.nValue;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;
import com.diacoipj.airdropreminder.tools.DayEntity;
import com.diacoipj.airdropreminder.tools.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragGetReminderInfo extends CustomFragment {
    String type;
    NumberPicker yearPickerSignIn;
    NumberPicker monthPickerSignIn;
    NumberPicker dayPickerSignIn;
    NumberPicker yearPickerReminder;
    NumberPicker monthPickerReminder;
    NumberPicker dayPickerReminder;
    NumberPicker NPHour;
    NumberPicker NPMinute;
    TextView Cancel,Ok;
    EditText SiteAddress,Avgmoney,Desc,Twitter,Discord;
    String SignInTime="";
    String [] listMin , listHour;
    String Insert="insert";
    EditText walletType,ChainType;

    int MonthNumberDay[]={31,28,31,30,31,30,31,31,30,30,30,31};
    @Override
    public int layout() {
        return R.layout.frag_getinfo_reminder;
    }
    //for insert
    public FragGetReminderInfo getInsertInstance(String Type){
        FragGetReminderInfo fragGetReminderInfo=new FragGetReminderInfo();
        Bundle bundle=new Bundle();
        bundle.putString("type",Type);
        bundle.putString("insert","insert");
        fragGetReminderInfo.setArguments(bundle);
        return fragGetReminderInfo;
    }
    //For edit
    public FragGetReminderInfo getUpdateInstance2(String Type, String Text, ReminderData reminderData){
        FragGetReminderInfo fragGetReminderInfo=new FragGetReminderInfo();
        Bundle bundle=new Bundle();
        bundle.putString("type",Type);
        bundle.putString("remindertext",Text);
        bundle.putString("insert","edit");
        bundle.putSerializable("reminder",reminderData);
        fragGetReminderInfo.setArguments(bundle);
        return fragGetReminderInfo;
    }

    ReminderData reminderData = new ReminderData() ;
    @Override
    public void onCreateMyView() {
        type=getStringBundle("type");
        Insert=getStringBundle("insert");
        MainActivity.getGlobal().TypeOfReminder=type;
        SetView();
        List<String> minArrayList = new ArrayList<>();
        List<String> hourArrayList = new ArrayList<>();
        Cancel.setOnClickListener(v -> mBackPressed());
        onSetPickers();
        onSetTimePickers(NPMinute,1 ,60,0,0,listMin ,minArrayList);
        onSetTimePickers(NPHour,1 ,24,0,0,listHour ,hourArrayList);
        NPHour.setOnValueChangedListener((picker, oldVal, newVal) -> {
            hour=String.valueOf(newVal-1);
            if (hour.equals("0"))
                hour="24";

        });
        NPMinute.setOnValueChangedListener((picker, oldVal, newVal) -> min=String.valueOf(newVal-1));

        if(Insert.equals("edit")){
            reminderData=getObjectBundle("reminder");
            setReminderEdit();
        }
        Ok.setOnClickListener(v -> {
            mAnimation.PressClick(v);
            if(checkValue()) {
                nValue.getGlobal().setTime(hour + ":" + min);
                nValue.getGlobal().setRDate(year + "-" + month + "-" + day);
                setDates();
                int thisHour = Integer.parseInt(getTimeCurrent().split(":")[0]);
                int thisMin = Integer.parseInt(getTimeCurrent().split(":")[1]);
                if(isLeapYear(year))
                    MonthNumberDay[1]=29;
                else
                    MonthNumberDay[1]=28;
                if (MonthNumberDay[month - 1] < day) {
                    MainActivity.getGlobal().showSnackBar("warning", MainActivity.getGlobal().getResources().getString(R.string.values2), 1500);
                } else if ((year <= thisYEAR) && ((month < thisMonth) || (month == thisMonth && day < thisDay) || (month == thisMonth && day == thisDay && Integer.parseInt(hour) < thisHour) || (month == thisMonth && day == thisDay && Integer.parseInt(hour) == thisHour && Integer.parseInt(min) < thisMin))) {
                    MainActivity.getGlobal().showSnackBar("warning", MainActivity.getGlobal().getResources().getString(R.string.reminders), 1500);
                } else {
                    nValue.getGlobal().setRDate(year + "-" + month + "-" + day);
                    onSetInDB();
                }
            }
            else{
                MainActivity.getGlobal().showSnackBar("warning", "Please fill all field", 1500);
            }
        });
        set();
        setChain();

        walletType.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) {
                if (!ShowDialog) {
                    hideDialogChain();
                    addDialogWallet();
                } else
                    hideDialogWallet();
            }
        });
        ChainType.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) {
                if (!showDialogChain) {
                    hideDialogWallet();
                    addDialogChain();
                } else
                    hideDialogChain();
            }
        });
    }

    private void setReminderEdit() {
        nValue.getGlobal().setTime(reminderData.getAlertHour());
        nValue.getGlobal().setRDate(reminderData.getAlertDate());
        Desc.setText(reminderData.getNote());
        Avgmoney.setText(reminderData.getValue());
        SiteAddress.setText(reminderData.getSiteAddress());
        SignInTime=reminderData.getSubmitDate();
        String date=reminderData.getAlertDate();
        String time=reminderData.getAlertHour();
        year=Integer.parseInt(date.split("-")[0]);
        month=Integer.parseInt(date.split("-")[1]);
        day=Integer.parseInt(date.split("-")[2]);
        hour=time.split(":")[0];
        min=time.split(":")[1];
        yearPickerSignIn.setValue(SignInTime.split("-")[0].equals("2021")?1:2);
        monthPickerSignIn.setValue(Integer.parseInt(SignInTime.split("-")[1]));
        dayPickerSignIn.setValue(Integer.parseInt(SignInTime.split("-")[2]));
        yearPickerReminder.setValue(date.split("-")[0].equals("2021")?1:2);
        monthPickerReminder.setValue(Integer.parseInt(date.split("-")[1]));
        dayPickerReminder.setValue(Integer.parseInt(date.split("-")[2]));
        NPHour.setValue(time.split(":")[0].equals("24")?1:Integer.parseInt(time.split(":")[0])+1);
        NPMinute.setValue(Integer.parseInt(time.split(":")[1])+1);
        id=reminderData.getId();
        walletType.setText(reminderData.getWalletType());
        Discord.setText(reminderData.getDiscordLink());
        Twitter.setText(reminderData.getTwitter());
        ChainType.setText(reminderData.getCahinType());

    }

    private void SetView() {
        monthPickerSignIn = parent.findViewById(R.id.npkSignInMonth);
        dayPickerSignIn = parent.findViewById(R.id.npkSignInDays);
        yearPickerSignIn = parent.findViewById(R.id.npkSignInYear);
        monthPickerReminder = parent.findViewById(R.id.npkRememberMonth);
        dayPickerReminder = parent.findViewById(R.id.npkRememberDays);
        yearPickerReminder = parent.findViewById(R.id.npkRememberYear);
        Cancel=parent.findViewById(R.id.txtCancelRemember);
        Ok=parent.findViewById(R.id.txtAcceptRemember);
        SiteAddress=parent.findViewById(R.id.AddressSite);
        Avgmoney=parent.findViewById(R.id.AvgCost);
        NPHour = parent.findViewById(R.id.npkRememberHour);
        NPMinute = parent.findViewById(R.id.npkRememberMinute);
        Desc=parent.findViewById(R.id.Desc);
        Twitter=parent.findViewById(R.id.twitterLink);
        Discord=parent.findViewById(R.id.disLink);
        walletType=parent.findViewById(R.id.walletType);
        ChainType=parent.findViewById(R.id.ChainType);
    }

    @Override
    public void mBackPressed() {
        super.mBackPressed();
        MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());
    }


    List<String> years = new ArrayList<>();
    List<String> days = new ArrayList<>();
    String[] yearsList = new String[6];
    String[] daysList = new String[31];
    String[] monthList = new String[12];
    int month=1 , day = 1 , year=1399;
    String min="00" , hour="00";
    List<DayEntity> Current;
    List<DayEntity> Prev;
    String[] now;
    private Utils utils;
    int max;
    String date;
    public  void onSetPickers(){
        utils = Utils.getInstance(getContext());
        /*String Date=getDateTime();
        String Split[]=Date.replaceAll(" ","").split("-");*/
        date = new DateManager().getTodayDate( false, false,"");
        now = date.split(date.contains("/")?"/":"-");
        max = utils.getDays(0).get(0).getPersianDate().getYear();
        year = max;
        Current = utils.getDays(0);
        Prev = utils.getDays(1);
        for (int i = 0; i < 6; i++)
        {
            years.add(String.valueOf(max +i));
            yearsList[i] = years.get(i);
        }

        for (int i = 0; i < 31; i++) {
            if (i < 9 && i != 0)
                days.add((""+(i + 1)));
            else days.add(String.valueOf(i + 1));
            daysList[i] = days.get(i);
        }
        month = Current.get(0).getPersianDate().getMonth();
        day = Integer.parseInt(now[2]);
        monthList = DateManager.MonthNames(getContext());
        new DateManager().createNumPickerWithStringVals(monthPickerReminder, 1, 12, monthList);
        monthPickerReminder.setOnValueChangedListener((numberPicker, i, i1) -> {
            month=i1;
        });
        monthPickerReminder.setValue(Integer.parseInt(now[1]));
        new DateManager().createNumPickerWithStringVals(monthPickerSignIn, 1, 12, monthList);
        monthPickerSignIn.setValue(Integer.parseInt(now[1]));
//        ----------------------------year---------------------------------------
        new DateManager().createNumPickerWithStringVals(yearPickerReminder, 1, 6, yearsList);
        yearPickerReminder.setOnValueChangedListener((numberPicker, i, i1) -> {
            year = max+i1-1;
        });
        new DateManager().createNumPickerWithStringVals(yearPickerSignIn, 1, 2, yearsList);
//        ------------------------------day--------------------------------------
        new DateManager().createNumPickerWithStringVals(dayPickerReminder, 1, 31, daysList);
        dayPickerReminder.setOnValueChangedListener((numberPicker, i, i1) -> {
            day = i1;
        });
        dayPickerReminder.setValue(Integer.parseInt(now[2]));
        new DateManager().createNumPickerWithStringVals(dayPickerSignIn, 1, 31, daysList);
        dayPickerSignIn.setValue(Integer.parseInt(now[2]));
    }

    boolean check;
    int id =-1;
    int [] Miladi ;
    public void onSetInDB(){
        SignInTime=yearPickerSignIn.getValue()==2?"2022":"2021"+"-"+monthPickerSignIn.getValue()+"-"+dayPickerSignIn.getValue();
        String[] separated = nValue.getGlobal().getRDate().split( nValue.getGlobal().getRDate().contains("/")?"/":"-");
        YEAR =  Integer.parseInt(separated[0]);
        Month = Integer.parseInt(separated[1]);
        Day = Integer.parseInt(separated[2]);
        if(Insert.equals("insert"))
        {
            SendToServer("insert");
        }else{
            new AlaramManager(getContext(),Integer.parseInt(hour) , Integer.parseInt(min),id,isNotifEnable(),"","","").cancle();
            SendToServer("edit");
        }

    }

    private void SendToServer(  String edit) {
        parent.findViewById(R.id.progress).setVisibility(View.VISIBLE);
        Ok.setVisibility(View.GONE);
        Cancel.setClickable(false);
        ReminderData reminderData=new ReminderData();
        reminderData.setId(id);
        reminderData.setAlertDate(nValue.getGlobal().getRDate());
        reminderData.setAlertHour(nValue.getGlobal().getTime());
        reminderData.setNote(Desc.getText().toString());
        reminderData.setType(type.equals("ido")?1:2);
        reminderData.setSiteAddress(SiteAddress.getText().toString());
        reminderData.setValue(Avgmoney.getText().toString());
        reminderData.setSubmitDate(SignInTime);
        reminderData.setTag("other");
        reminderData.setFlag(1);
        reminderData.setTwitter(Twitter.getText().toString());
        reminderData.setWalletType(walletType.getText().toString());
        reminderData.setDiscordLink(Discord.getText().toString());
        reminderData.setCahinType(ChainType.getText().toString());
        if(edit.equals("insert")) {

            Presenter.get_global().PostAction(new IView<ResponseObject>() {
                @Override
                public void SendRequest() { }
                @Override
                public void OnSucceed(ResponseObject object) {
                    parent.findViewById(R.id.progress).setVisibility(View.GONE);
                    MainActivity.getGlobal().showSnackBar("accept", "Saved", 1000);
                    onSetAlarm(Desc.getText().toString(),object.getItemID());
                    MainActivity.getGlobal().FinishFragStartFragCustomAnimation(new FragReminder(), R.anim.slide_in_up, R.anim.slide_in_down);
                }
                @Override
                public void OnError(String error, int statusCode) {
                    parent.findViewById(R.id.progress).setVisibility(View.GONE);
                    MainActivity.getGlobal().showSnackBar("reject", "can't Save", 1000);
                    Ok.setVisibility(View.VISIBLE);
                    Cancel.setClickable(true);
                }
            }, "crypto", "submitItem", "",reminderData , ResponseObject.class);
        }
        else{
            parent.findViewById(R.id.progress).setVisibility(View.VISIBLE);
            Presenter.get_global().PostAction(new IView<ResponseObject>() {
                @Override
                public void SendRequest() { }

                @Override
                public void OnSucceed(ResponseObject object) {
                    parent.findViewById(R.id.progress).setVisibility(View.GONE);
                    MainActivity.getGlobal().showSnackBar("accept", "Saved", 1000);
                    onSetAlarm(Desc.getText().toString(),reminderData.getId());
                    MainActivity.getGlobal().FinishFragStartFragCustomAnimation(new FragReminder(), R.anim.slide_in_up, R.anim.slide_in_down);
                }

                @Override
                public void OnError(String error, int statusCode) {
                    parent.findViewById(R.id.progress).setVisibility(View.GONE);
                    MainActivity.getGlobal().showSnackBar("reject", "can't Save", 1000);
                }
            }, "crypto", "updateItem", "", reminderData, ResponseObject.class);
        }
    }

    int  YEAR , Month, Day , thisYEAR , thisMonth , thisDay;
    public  void setDates(){
        String[] separated = nValue.getGlobal().getRDate().split( nValue.getGlobal().getRDate().contains("/")?"/":"-");
        YEAR =  Integer.parseInt(separated[0]);
        Month = Integer.parseInt(separated[1]);
        Day = Integer.parseInt(separated[2]);
        thisYEAR = Integer.parseInt(now[0]);
        thisMonth = Integer.parseInt(now[1]);
        thisDay= Integer.parseInt(now[2]);

        Miladi = ShamsiToMiladi(separated[0] , separated[1] , separated[2]);

    }

    public int [] ShamsiToMiladi(String Year , String Month , String Day) {
        return new int[]{Integer.parseInt(Year) , Integer.parseInt(Month) , Integer.parseInt(Day)};
    }

    public  void onSetAlarm(String desc,int id){
        String dateChoose = nValue.getGlobal().getRDate();
        String [] sp = dateChoose.split(dateChoose.contains("-") ? "-" : "/");
        Miladi = new int[]{Integer.parseInt(sp[0]),Integer.parseInt(sp[1]),Integer.parseInt(sp[2])};
        String time = nValue.getGlobal().getTime();
        String title=type.equals("ido")?"IDO Reminder":"AirDrop Reminder";
        if(nValue.getGlobal().getRDate().equals(new DateManager().getTodayDate( false, false,"").replaceAll("/","-"))){
            new AlaramManager(MainActivity.getGlobal() , Integer.parseInt(time.split(":")[0]),Integer.parseInt(time.split(":")[1]), id, isNotifEnable(),desc,title,SiteAddress.getText().toString());
        } else  if(YEAR>=thisYEAR &&   Month>=thisMonth &&   Day>thisDay  ){
            new AlaramManager(getContext() , -1 , -1 , id , false,desc,title,SiteAddress.getText().toString()).setNotificationReminder(Miladi , Integer.parseInt(time.split(":")[0]) , Integer.parseInt(time.split(":")[1]) ,id );
        }
    }

    public boolean isNotifEnable(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(getContext()))
                return true;
            else
                return false ;
        } else
            return false ;
    }


    public  void onSetTimePickers(NumberPicker picker , int value , int max , int start , int changePick ,    String [] list,  List<String> numArrayList  ){
        list =  new String[max];
        picker.setMinValue(value);
        picker.setMaxValue(max);
        picker.setDisplayedValues(DateManager.generateNumbers(start, max, list, numArrayList, changePick , "0" , ""));
    }

    private String getTimeCurrent () {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateToStr = format.format(today);
        String[] sp = Setting.textSeprator(dateToStr, ":");
        return sp[0] + ":" + sp[1];
    }

    public boolean checkValue(){
       if(SiteAddress.getText().toString().equals(""))
            return false;
        return true;
    }


    boolean ShowDialog,showDialogChain ;
    public WAdapter ada;
    public  void addDialogWallet(){
        ShowDialog=true;
        ((RelativeLayout)parent.findViewById(R.id.dialog_type)).removeAllViews();
        parent.findViewById(R.id.dialog_type).setVisibility(VISIBLE);
        ((RelativeLayout) parent.findViewById(R.id.dialog_type)).addView(new WalletTypeList(getContext(), FragGetReminderInfo.this,"wallet"));
        new Setting().TransitionResize(parent.findViewById(R.id.rel_edit_text));
    }
    public void hideDialogWallet() {
        ShowDialog = false;
        ((RelativeLayout) parent.findViewById(R.id.dialog_type)).removeAllViews();
        parent.findViewById(R.id.dialog_type).setVisibility(View.GONE);
    }

    public List<WalletsTypes> models = new ArrayList<>();
    public void set() {
        models.add(new WalletsTypes(1, "MetaMask"));
        models.add(new WalletsTypes(2, "TrustWallet"));
        models.add(new WalletsTypes(3, "Coinomi"));
        models.add(new WalletsTypes(4, "Atomic"));

    }

    public  void addDialogChain(){
        showDialogChain=true;
        ((RelativeLayout)parent.findViewById(R.id.dialog_Chaintype)).removeAllViews();
        parent.findViewById(R.id.dialog_Chaintype).setVisibility(VISIBLE);
        ((RelativeLayout) parent.findViewById(R.id.dialog_Chaintype)).addView(new WalletTypeList(getContext(), FragGetReminderInfo.this,"chain"));
        new Setting().TransitionResize(parent.findViewById(R.id.rel_edit_text_chain));
    }
    public void hideDialogChain() {
        showDialogChain = false;
        ((RelativeLayout) parent.findViewById(R.id.dialog_Chaintype)).removeAllViews();
        parent.findViewById(R.id.dialog_Chaintype).setVisibility(View.GONE);
    }


    public List<WalletsTypes> modelChain = new ArrayList<>();

    public void setChain() {
        modelChain.add(new WalletsTypes(1, "Bsc"));
        modelChain.add(new WalletsTypes(2, "Sol"));
        modelChain.add(new WalletsTypes(3, "Eth"));
        modelChain.add(new WalletsTypes(4, "Polygon"));
        modelChain.add(new WalletsTypes(5, "Xdai"));
        modelChain.add(new WalletsTypes(6, "Algo"));
        modelChain.add(new WalletsTypes(7, "Iota"));
        modelChain.add(new WalletsTypes(8, "Harmony"));
        modelChain.add(new WalletsTypes(9, "Ftm"));
        modelChain.add(new WalletsTypes(10, "Avalanch"));

    }

    public boolean isLeapYear(int year){

        boolean leap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }
            else
                leap = true;
        }
        else
            leap = false;

        return leap;
    }
}
