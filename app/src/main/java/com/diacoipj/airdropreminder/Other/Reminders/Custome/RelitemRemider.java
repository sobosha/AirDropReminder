package com.diacoipj.airdropreminder.Other.Reminders.Custome;

import android.content.Context;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.Main.ResponseObject;
import com.diacoipj.airdropreminder.Other.Reminders.FragGetReminderInfo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.AlarmManager.AlaramManager;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.DateMiladi;
import com.diacoipj.airdropreminder.Setting.DateShamsi;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.List;

public class RelitemRemider extends RelativeLayout {
    RelativeLayout RelInsideBody;

    public RelitemRemider(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rel_creminder_item, this, true);
        RelInsideBody = findViewById(R.id.relInsideBody);
        //RelInsideBody.setOnTouchListener(this);
    }

    int month , day ;
    int hour , min ;
    boolean isAlltext = false;
    FragReminder fragReminder ;
    boolean Expand=false;
    String url="";
    public void OnStart(ReminderData item, int position , List<ReminderData>  list , AdapterReminder adapterReminder , FragReminder fragReminder)
    {

        this.fragReminder = fragReminder ;
        String lastDate = item.getAlertDate() ;
        String[] separated = lastDate.split(lastDate.contains("/")?"/":"-");
        day=Integer.parseInt(separated[2]);
        month= Integer.parseInt(separated[1]);

        String time []= item.getAlertHour().split(":");
        hour =Integer.valueOf( time[0]);
        min =Integer.valueOf( time[1]);

        ((TextView)findViewById(R.id.txtDesc)).setText(item.getNote());
        ((TextView)findViewById(R.id.txtAll)).setText(item.getText());
        ((TextView)findViewById(R.id.txtDay)).setText(String.valueOf(day));
        ((TextView)findViewById(R.id.txtMonth)).setText(new DateShamsi().persianMonth(month));
        ((TextView)findViewById(R.id.txtTime)).setText(item.getAlertHour());

        setStyle(item,position);
        findViewById(R.id.imgExpand).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);

                Expand(item,Expand);
                Expand= !Expand;
                ((ImageView)findViewById(R.id.imgExpand)).setScaleY(Expand==true?-1:1);
            }
        });
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Expand(item,Expand);
                Expand= !Expand;
                ((ImageView)findViewById(R.id.imgExpand)).setScaleY(Expand==true?-1:1);
            }
        });
        findViewById(R.id.imgShare).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody=item.getAlertDate()+"\n"+item.getAlertHour()+"\n"+item.getSiteAddress()+"\n"+item.getValue()+"\n"+item.getTwitter()+"\n"+item.getCahinType()+"\n"+item.getWalletType()+"\n"+
                        item.getNote()+"\n"+item.getSubmitDate()+"\n"+item.getDiscordLink()+"\n"+"-------------------";
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                MainActivity.getGlobal().startActivity(Intent.createChooser(intent,"Choose"));
            }
        });
        findViewById(R.id.relWebsite).setOnClickListener(v -> {
            url=item.getSiteAddress();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            MainActivity.getGlobal().startActivity(browserIntent);
        });
        findViewById(R.id.relDiscord).setOnClickListener(v -> {
            url=item.getDiscordLink();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            MainActivity.getGlobal().startActivity(browserIntent);
        });
        findViewById(R.id.relTwitter).setOnClickListener(v -> {
            url=item.getTwitter();
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            MainActivity.getGlobal().startActivity(browserIntent);
        });
        findViewById(R.id.imgDelete).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ButtonWithListeners(true, "Delete", "Are you shure for delete?", "Yes", "No", v12 -> {
                mAnimation.PressClick(v12);
                Presenter.get_global().PostAction(new IView<ResponseObject>() {
                    @Override
                    public void SendRequest() {

                    }

                    @Override
                    public void OnSucceed(ResponseObject object) {
                        new AlaramManager(getContext(),hour , min,item.getId(),isNotifEnable(),"","","").cancle();
                        MainActivity.getGlobal().HideMessageBox();
                        MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());

                    }

                    @Override
                    public void OnError(String error, int statusCode) {
                        MainActivity.getGlobal().showSnackBar("reject","we have problem",1000);
                    }
                },"crypto","delItem","",item,ResponseObject.class);
            }, v1 -> {
                mAnimation.PressClick(v1);
                MainActivity.getGlobal().HideMessageBox();
            },"#FF0000",R.drawable.red_alert).setBackOKBtn(R.drawable.shape_redbtn).setBackCancelBtn(R.drawable.shape_btn_cancel));

         });

        findViewById(R.id.imgEdit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                MainActivity.getGlobal().FinishFragStartFrag(new FragGetReminderInfo().getUpdateInstance2(item.getType()==2?"airdrop":"ido","",item));
                //MainActivity.getGlobal().MainDiallog("reminderDialog" ,1 ,null , true,item.getId());
            }
        });



        if (item.getDaysReminderList().isEmpty()) {
            findViewById(R.id.txtDaysName).setVisibility(GONE);
            findViewById(R.id.linDate).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.txtDaysName).setVisibility(GONE);
            findViewById(R.id.linDate).setVisibility(VISIBLE);
        }


        if (!item.getDaysReminderList().isEmpty() && item.getDaysReminderList().size()<7) {
            new Setting().setTypeFace(findViewById(R.id.txtDaysName));
            StringBuilder dayName = new StringBuilder();
            for (int i = 1; i < item.getDaysReminderList().size()+1 ; i++) {

                dayName.append(DateMiladi.getDay(Integer.parseInt(item.getDaysReminderList().get(i-1))));
                if (i%2 == 0) {
                    dayName.append("\n");
                }
                if (i==1 || i==3 || i==5) {
                    dayName.append(" ");
                }
                ((TextView)findViewById(R.id.txtDaysName)).setText(dayName);
            }
        } else if (!item.getDaysReminderList().isEmpty()){
            new Setting().setTypeFace(findViewById(R.id.txtDaysName));
            ((TextView)findViewById(R.id.txtDaysName)).setText("هر روز");
        }
        if(list.indexOf(item)!=0){
            if(item.getAlertDate().equals(list.get(list.indexOf(item)-1).getAlertDate())){
                findViewById(R.id.linDate).setVisibility(GONE);
            }
            else{
                findViewById(R.id.linDate).setVisibility(VISIBLE);
            }
        }
    }

    String color= "#ffcc66";
    public  void setStyle(ReminderData item,int pos){
        if(item.getFlag()==1){
            findViewById(R.id.lineDay).setVisibility(GONE);
            findViewById(R.id.lineMonth).setVisibility(GONE);
            findViewById(R.id.lineDesc).setVisibility(GONE);
            findViewById(R.id.imgPlusDay).setVisibility(GONE);
        } else {
            findViewById(R.id.lineDay).setVisibility(VISIBLE);
            findViewById(R.id.lineMonth).setVisibility(VISIBLE);
            findViewById(R.id.lineDesc).setVisibility(VISIBLE);
            tickBack();
        }
        if(pos%4==0){
            findViewById(R.id.linDate).setBackgroundResource(R.drawable.shape_month_reminder_blue);
            ((TextView)findViewById(R.id.txtDay)).setTextColor(Color.parseColor("#62b4fe"));
            ((TextView)findViewById(R.id.txtMonth)).setTextColor(Color.parseColor("#62b4fe"));
            ((TextView)findViewById(R.id.txtDesc)).setTextColor(Color.parseColor("#62b4fe"));
        }
        else if(pos%4==1){
            findViewById(R.id.linDate).setBackgroundResource(R.drawable.shape_month_reminder_red);
            ((TextView)findViewById(R.id.txtDay)).setTextColor(Color.parseColor("#ff7461"));
            ((TextView)findViewById(R.id.txtMonth)).setTextColor(Color.parseColor("#ff7461"));
            ((TextView)findViewById(R.id.txtDesc)).setTextColor(Color.parseColor("#ff7461"));
        }
        else if(pos%4==2){
            findViewById(R.id.linDate).setBackgroundResource(R.drawable.shape_month_reminder_yellow);
            ((TextView)findViewById(R.id.txtDay)).setTextColor(Color.parseColor("#f39c12"));
            ((TextView)findViewById(R.id.txtMonth)).setTextColor(Color.parseColor("#f39c12"));
            ((TextView)findViewById(R.id.txtDesc)).setTextColor(Color.parseColor("#f39c12"));
        }
        else if(pos%4==3){
            findViewById(R.id.linDate).setBackgroundResource(R.drawable.shape_month_reminder_green);
            ((TextView)findViewById(R.id.txtDay)).setTextColor(Color.parseColor("#2ecc71"));
            ((TextView)findViewById(R.id.txtMonth)).setTextColor(Color.parseColor("#2ecc71"));
            ((TextView)findViewById(R.id.txtDesc)).setTextColor(Color.parseColor("#2ecc71"));
        }
        //new Setting().changeSvgColor(getContext(), findViewById(R.id.imgEdit) ,R.color.veryDarkSorme);
        //new Setting().changeSvgColor(getContext(), findViewById(R.id.imgDelete) ,R.color.veryDarkSorme);
        //int flag ; // 1 active  - 2 deactive - 3 done
        if (item.getFlag() == 1) {
            /*((TextView) findViewById(R.id.txtDesc)).setTextColor(MainActivity.getGlobal().getResources().getColor(R.color.veryDarkSorme));
            ((TextView) findViewById(R.id.txtTime)).setTextColor(MainActivity.getGlobal().getResources().getColor(R.color.veryDarkSorme));
            color = "#e1eefa";*/
        } else {
            //((TextView) findViewById(R.id.txtDesc)).setTextColor(MainActivity.getGlobal().getResources().getColor(R.color.colorWhite));
            //((TextView) findViewById(R.id.txtTime)).setTextColor(MainActivity.getGlobal().getResources().getColor(R.color.colorWhite));
            findViewById(R.id.linDate).setBackgroundResource(R.drawable.shape_month_reminder_gray);
            ((TextView)findViewById(R.id.txtDay)).setTextColor(Color.parseColor("#707070"));
            ((TextView)findViewById(R.id.txtMonth)).setTextColor(Color.parseColor("#707070"));
            ((TextView)findViewById(R.id.txtDesc)).setTextColor(Color.parseColor("#707070"));
            color = "#26707070";
        }

        /*if ((item.getDate().equals(new DateManager().getTodayDate(false, false, "-1")) && item.getFlag() == 1) || !item.getDaysReminderList().isEmpty()) {
            ((TextView) findViewById(R.id.txtDesc)).setTextColor(MainActivity.getGlobal().getResources().getColor(R.color.veryDarkSorme));
            ((TextView) findViewById(R.id.txtTime)).setTextColor(MainActivity.getGlobal().getResources().getColor(R.color.veryDarkSorme));
            color = "#ffcc66";
        }

        if (item.getFlag() == 0) {
            color = "#ffcc66";
            findViewById(R.id.lineDay).setVisibility(GONE);
            findViewById(R.id.lineMonth).setVisibility(GONE);
            findViewById(R.id.lineDesc).setVisibility(GONE);
            tickBack();
        }*/
        setColorStyle(true);
        new Setting().setTypeFace(findViewById(R.id.txtDay));
        findViewById(R.id.txtAll).setBackground(Setting.setBackGradiantFullRad(getContext(), Color.parseColor("#BBffffff"), 0,
                MainActivity.getGlobal().getResources().getDimension(R.dimen._10sdp)));

    }
    private void tickBack () {
        findViewById(R.id.imgPlusDay).setVisibility(VISIBLE);
        findViewById(R.id.imgPlusDay).setBackground(Setting.setBackWithStroke(getContext() ,
                Color.parseColor("#9287ff") ,
                Color.parseColor("#9287ff") ,
                MainActivity.getGlobal().getResources().getDimension(R.dimen._100sdp)));
    }


    public void setColorStyle(boolean isFullRad) {
        if(isFullRad) {
            if(color.equals("#26707070"))
                findViewById(R.id.relInsideBody).setBackground(new Setting().setBackColor(Color.parseColor(color), Color.parseColor(color), MainActivity.getGlobal().getResources().getDimension(R.dimen._15sdp)));
        } else {
            findViewById(R.id.relInsideBody).setBackground(Setting.setBackGradiantWithRadiuse(Color.parseColor(color) ,0,
                    0, MainActivity.getGlobal().getResources().getDimension(R.dimen._15sdp)  , 0,0
                    , MainActivity.getGlobal().getResources().getDimension(R.dimen._15sdp)));
        }
    }

    public boolean isNotifEnable(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(getContext()))
                return true;
            else
                return false ;
        } else
            return false;
    }

    private void Expand(ReminderData item,boolean expand){
        if(!expand){
            findViewById(R.id.RellinkExpand).setVisibility(VISIBLE);
            if(!item.getDiscordLink().equals(""))
                ((TextView)findViewById(R.id.TextLinkDiscord)).setText(item.getDiscordLink());
            else findViewById(R.id.relDiscord).setVisibility(GONE);
            if(!item.getTwitter().equals(""))
                ((TextView)findViewById(R.id.TextLinkTwitter)).setText(item.getTwitter());
            else findViewById(R.id.relTwitter).setVisibility(GONE);
            if(!item.getSiteAddress().equals(""))
                ((TextView)findViewById(R.id.TextLinkWeb)).setText(item.getSiteAddress());
            else findViewById(R.id.relWebsite).setVisibility(GONE);
            if(!item.getWalletType().equals(""))
                ((TextView) findViewById(R.id.TextCoinBase)).setText(item.getWalletType());
            else findViewById(R.id.TextCoinBase).setVisibility(GONE);
            findViewById(R.id.GapLine).setVisibility(VISIBLE);
            if(item.getValue().equals(""))
                findViewById(R.id.TextMoneyCost).setVisibility(GONE);
            else ((TextView)findViewById(R.id.TextMoneyCost)).setText(item.getValue()+"$");
            findViewById(R.id.relCoinDollar).setVisibility((findViewById(R.id.TextMoneyCost).getVisibility()==GONE && findViewById(R.id.TextCoinBase).getVisibility()==GONE)?GONE:VISIBLE);

        }
        else{
            findViewById(R.id.RellinkExpand).setVisibility(GONE);
            findViewById(R.id.GapLine).setVisibility(GONE);
        }
    }


}
