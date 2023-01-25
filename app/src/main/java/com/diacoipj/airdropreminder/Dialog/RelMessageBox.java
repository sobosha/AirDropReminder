package com.diacoipj.airdropreminder.Dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;


public class RelMessageBox extends RelativeLayout {
    public RelMessageBox(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rel_message_box, this, true);

        findViewById(R.id.imgBackGround).setBackgroundResource(R.drawable.shape_dialog);


        new Setting().setTypeFace(findViewById(R.id.btnOkay));
        new Setting().setTypeFace(findViewById(R.id.btnCancle));
        findViewById(R.id.imgBack).setOnClickListener(v -> {
            if (!disableBack)
                MainActivity.getGlobal().HideMessageBox();
        });

    }

    public  RelMessageBox ExitButton( String type, String desc ){
        findViewById(R.id.btnCancle).setVisibility(View.GONE);

        ((TextView)findViewById(R.id.txtDesc)).setText(desc);
        if(type.equals("connectionError")) {
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.blue_alert);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#406eff"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            ((TextView) findViewById(R.id.btnOkay)).setText(MainActivity.getGlobal().getResources().getString(R.string.OK));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_blue_btn);
        } else if(type.equals("error")){
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.red_alert);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#cc1414"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_red_btn);
        }else if(type.equals("info")) {
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.ic_info_icon_new);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#406eff"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            ((TextView) findViewById(R.id.btnOkay)).setText(MainActivity.getGlobal().getResources().getString(R.string.OK));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_blue_btn);
        }
        else {
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.green_alert);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#17bd79"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_green_btn);
        }
        findViewById(R.id.btnOkay).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            MainActivity.getGlobal().HideMessageBox();
        });

        findViewById(R.id.imgBack).setOnClickListener(v -> {
            MainActivity.getGlobal().HideMessageBox();
        });

        return this;
    }


    public  RelMessageBox showDialogTracking( String desc, View.OnClickListener onClickListener, int image,String btnOkText ){
        if (image==0)
            findViewById(R.id.imgIcon).setVisibility(GONE);
        else ((ImageView)findViewById(R.id.imgIcon)).setImageResource(image);

        findViewById(R.id.btnCancle).setOnClickListener(v -> {
            mAnimation.PressClick(v);
            MainActivity.getGlobal().HideMessageBox();
        });

        ((TextView)findViewById(R.id.txtDesc)).setText(desc);
        ((TextView)findViewById(R.id.btnOkay)).setText(btnOkText);
        ((TextView)findViewById(R.id.btnCancle)).setText("انصراف");
        findViewById(R.id.btnOkay).setOnClickListener(onClickListener);
        findViewById(R.id.imgBack).setOnClickListener(v -> MainActivity.getGlobal().HideMessageBox());

        return this;
    }


    public RelMessageBox setTextBtnOk (String text) {
        ((TextView) findViewById(R.id.btnOkay)).setText(text);
        return this ;
    }

    public RelMessageBox setTextBtnCancel (String text) {
        ((TextView) findViewById(R.id.btnCancle)).setText(text);
        return this ;
    }

    public RelMessageBox setIconTitle (int drawable) {
        findViewById(R.id.imgIcon).setBackgroundResource(drawable);
        return this ;
    }
    public RelMessageBox setStrokeColor (int color) {
        findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,color,MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
        return this ;
    }
    public RelMessageBox setBackOKBtn (int drawable) {
        findViewById(R.id.btnOkay).setBackgroundResource(drawable);
        return this ;
    }
    public RelMessageBox setBackCancelBtn (int drawable) {
        findViewById(R.id.btnCancle).setBackgroundResource(drawable);
        return this ;
    }

    public RelMessageBox onCancelClick (OnClickListener onClickListener) {
        findViewById(R.id.btnCancle).setOnClickListener(onClickListener);
        return this ;
    }
    public RelMessageBox onOkClick (OnClickListener onClickListener) {
        findViewById(R.id.btnOkay).setOnClickListener(onClickListener);
        return this ;
    }

    public RelMessageBox setDesc (String desc) {
        ((TextView)findViewById(R.id.txtDesc)).setText(desc);
        return this ;
    }

    private boolean disableBack ;
    public RelMessageBox disableClickBackImg () {
        disableBack = false ;
        return this ;
    }


    public  RelMessageBox ExitButtonWithClick(String type, String desc , OnClickListener onClickListener){
        findViewById(R.id.btnCancle).setVisibility(View.GONE);
        new Setting().setTypeFace(findViewById(R.id.txtDesc));
        ((TextView)findViewById(R.id.txtDesc)).setText(desc);
        if(type.equals("connectionError")) {
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.blue_alert);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#406eff"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            ((TextView) findViewById(R.id.btnOkay)).setText(MainActivity.getGlobal().getResources().getString(R.string.OK));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_blue_btn);
        } else if(type.equals("error")){
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.red_alert);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#cc1414"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_red_btn);
        } else {
            findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.green_alert);
            //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#17bd79"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
            findViewById(R.id.btnOkay).setBackgroundResource(R.drawable.shape_green_btn);
        }
        findViewById(R.id.btnOkay).setOnClickListener(onClickListener);

        findViewById(R.id.imgBack).setOnClickListener(v -> {
            MainActivity.getGlobal().HideMessageBox();
        });

        return this;
    }
    public RelMessageBox ButtonWithListeners(boolean isTwoButton  , String title ,String desc,String btnAction , String btnSecond ,OnClickListener rectBtnListener , OnClickListener circleBtnListener,String colorStroke  , int drawable){
        findViewById(R.id.imgIcon).setBackgroundResource(drawable);
        ((TextView)findViewById(R.id.txtDesc)).setText(desc);

//        if(!colorStroke.equals(""))
//             findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor(colorStroke),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));

        if(isTwoButton){
            findViewById(R.id.btnCancle).setOnClickListener(circleBtnListener);
        } else {
            findViewById(R.id.btnCancle).setOnClickListener(v -> MainActivity.getGlobal().HideMessageBox());
        }

        if(circleBtnListener==null){
            findViewById(R.id.btnCancle).setVisibility(GONE);
        }


        ((TextView)findViewById(R.id.btnOkay)).setText(btnAction);
        ((TextView)findViewById(R.id.btnCancle)).setText(btnSecond);
        findViewById(R.id.btnOkay).setOnClickListener(rectBtnListener);
        //todo remove this with fix bug music
        if(!btnAction.equals("موافقم")) {
            findViewById(R.id.imgBack).setOnClickListener(v -> {
                MainActivity.getGlobal().HideMessageBox();
            });
        }
        return this;

    }
    public RelMessageBox ButtonWithListeners(boolean isTwoButton  , String title ,String desc,String btnAction , String btnSecond ,OnClickListener rectBtnListener , OnClickListener circleBtnListener,String colorStroke  , String drawable){
        Setting.LoadImageWhitoutSize(getContext(), findViewById(R.id.imgIconRound), drawable, 0);
        ((TextView)findViewById(R.id.txtDesc)).setText(desc);

//        if(!colorStroke.equals(""))
//            findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor(colorStroke),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));

        if(isTwoButton){
            findViewById(R.id.btnCancle).setOnClickListener(circleBtnListener);
        } else {
            findViewById(R.id.btnCancle).setOnClickListener(v -> MainActivity.getGlobal().HideMessageBox());
        }

        if(circleBtnListener==null){
            findViewById(R.id.btnCancle).setVisibility(GONE);
        }


        ((TextView)findViewById(R.id.btnOkay)).setText(btnAction);
        ((TextView)findViewById(R.id.btnCancle)).setText(btnSecond);
        findViewById(R.id.btnOkay).setOnClickListener(rectBtnListener);
        //todo remove this with fix bug music
        if(!btnAction.equals("موافقم")) {
            findViewById(R.id.imgBack).setOnClickListener(v -> {
                MainActivity.getGlobal().HideMessageBox();
            });
        }
        return this;

    }


    public RelMessageBox setImageBitmap(Bitmap bitmap)
    {
        ((ImageView) findViewById(R.id.imgIcon)).setImageBitmap(bitmap);
        return this;
    }
    public RelMessageBox setImage(int image,String stokeColor,int btnDrawable)
    {
        findViewById(R.id.imgIcon).setBackgroundResource(image);
        if (!stokeColor.equals(""))
//        findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(),
//                android.R.color.transparent,
//                Color.parseColor(stokeColor),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));
        if (btnDrawable!=0)
        findViewById(R.id.btnOkay).setBackgroundResource(btnDrawable);

        return this;
    }


    public RelMessageBox ReminderDialog(FragReminder parent  ,String desc, OnClickListener rectBtnListener , OnClickListener circleBtnListener , int position){
        findViewById(R.id.imgIcon).setBackgroundResource(R.drawable.yellow_alert);
        ((TextView)findViewById(R.id.txtDesc)).setText(desc);
      //findViewById(R.id.imgStroke).setBackground(Setting.setBackWithStroke(getContext(), android.R.color.transparent,Color.parseColor("#17bd79"),MainActivity.getGlobal().getResources().getDimension(R.dimen._20sdp)));

        findViewById(R.id.btnCancle).setOnClickListener(circleBtnListener);

        if(circleBtnListener==null){
            findViewById(R.id.btnCancle).setVisibility(GONE);
        }

        ((TextView)findViewById(R.id.btnOkay)).setText(MainActivity.getGlobal().getResources().getString(R.string.OK));
        ((TextView)findViewById(R.id.btnCancle)).setText(   MainActivity.getGlobal().getResources().getString(R.string.notNow));
        findViewById(R.id.btnOkay).setOnClickListener(rectBtnListener);
        findViewById(R.id.imgBack).setOnClickListener(v -> {
            if(parent!=null  && position!=-1) {
                parent.getRelGeneral_items().data.setFlag(2);
                parent.getRelGeneral_items().adapter.notifyItemChanged(position);
            }
            MainActivity.getGlobal().HideMessageBox();
        });
        return this;

    }



    /*public RelMessageBox DialogNotIsBuyPackage (Hobbies_V2 hobbies_v2,OnClickListener onClickListener) {
        new Setting().setTypeFace(findViewById(R.id.txtDesc));
        findViewById(R.id.btnOkay).setVisibility(VISIBLE);
        findViewById(R.id.txtTitle).setVisibility(VISIBLE);
        ((TextView)findViewById(R.id.txtTitle)).setText(hobbies_v2.getPreview());
        ((TextView)findViewById(R.id.txtDesc)).setText(hobbies_v2.getTitle());
        Setting.LoadImageWhitoutSize(getContext() , findViewById(R.id.imgIconRound) , hobbies_v2.getImage() , R.drawable.place_holder_b);
        ((TextView)findViewById(R.id.btnOkay)).setText("پرداخت شهریه");
        ((TextView)findViewById(R.id.btnCancle)).setText("انصراف");

        findViewById(R.id.btnOkay).setOnClickListener(onClickListener);

        findViewById(R.id.btnCancle).setOnClickListener(view -> {
            mAnimation.PressClick(view);
            MainActivity.getGlobal().HideMessageBox();
        });
        findViewById(R.id.imgBack).setOnClickListener(v -> {
            MainActivity.getGlobal().HideMessageBox();
        });
        return this ;
    }*/



}
