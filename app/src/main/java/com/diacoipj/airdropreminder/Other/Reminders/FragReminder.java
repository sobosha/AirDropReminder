package com.diacoipj.airdropreminder.Other.Reminders;

import android.content.Intent;
import android.os.Bundle;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.Main.ReminderList;
import com.diacoipj.airdropreminder.Main.ResponseObject;
import com.diacoipj.airdropreminder.Setting.DateManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.diacoipj.airdropreminder.Dialog.DifficultsDialog.RelDifficultChoose;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.General._RelGeneral_items;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.ViewPagerAnimation.PopTransformation;
import com.diacoipj.airdropreminder.Setting.WrapViewPager;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mFragment;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.respons.Reminders.ReminderData;

import java.util.ArrayList;
import java.util.List;

public class FragReminder extends mFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View parent;
    WrapViewPager viewPager;
    List<ReminderData> customeData;//AIRDRop
    List<ReminderData> generalData;//IDO
    List<ReminderData> AllList=new ArrayList<>();
    _RelGeneral_items relGeneral_items;
    public int totalSize ;

    public _RelGeneral_items getRelGeneral_items() {
        return relGeneral_items;
    }

    public void setRelGeneral_items(_RelGeneral_items relGeneral_items) {
        this.relGeneral_items = relGeneral_items;
    }

    String back="";
    public FragReminder setBack(String back)
    {
        this.back = back;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View parent = inflater.inflate(R.layout.frag_reminder, container, false);
        this.parent = parent;

        mLocalData.SetCurrentDay(getContext(),new DateManager().getTodayDate(false, false,"-1"));
        customeData = new ArrayList<>();
        generalData = new ArrayList<>();
        getList();
        new Setting().setTypeFace(parent.findViewById(R.id.txtLeft));
        parent.findViewById(R.id.txtLeft).setOnClickListener(view -> MainActivity.getGlobal().FinishRelStartRel(new RelDifficultChoose(getContext())));
        parent.findViewById(R.id.relRight).setOnClickListener(v -> MainActivity.getGlobal().showDrawer());
        parent.findViewById(R.id.TextPermiumWait).setVisibility(isVip==0?View.VISIBLE:View.GONE);
        parent.findViewById(R.id.imgShareList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody="";
                for(int i=0;i<AllList.size();i++) {
                    shareBody += "\n"+AllList.get(i).getAlertDate() + "\n" + AllList.get(i).getAlertHour() + "\n" + AllList.get(i).getSiteAddress() + "\n" + AllList.get(i).getValue() + "\n" + AllList.get(i).getTwitter() + "\n" + AllList.get(i).getCahinType() + "\n" + AllList.get(i).getWalletType() + "\n" +
                            AllList.get(i).getNote() + "\n" + AllList.get(i).getSubmitDate() + "\n" + AllList.get(i).getDiscordLink() + "\n" + "-------------------";
                }
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                MainActivity.getGlobal().startActivity(Intent.createChooser(intent,"Choose"));
            }
        });
        return parent;
    }

    public  int freeCount ;
    public  int isVip ; //1 =  خرید موفق  0=خریده تایید نشده -1 = نخریده
    void getList(){
        progressStyle(0);
        Presenter.get_global().GetAction(new IView<ReminderList>() {
            @Override
            public void SendRequest() { }

            @Override
            public void OnSucceed(ReminderList object) {
                freeCount = object.getData().getFreeCount();
                isVip = object.getData().getVipUser() ;
                totalSize = object.getList().size() ;
                progressStyle(1);
                customeData.clear();
                generalData.clear();
                for(int i=0;i<object.getList().size();i++){
                    if(object.getList().get(i).getType()==2){
                        customeData.add(object.getList().get(i));
                    }
                    else{
                        generalData.add(object.getList().get(i));
                    }
                    AllList.add(object.getList().get(i));
                }
                MainActivity.getGlobal().WalletAddress=object.getData().getWalletAddress();
                MainActivity.getGlobal().Viptxt=object.getData().getVipText();
                onSetViewPager();
             //   onRefreshGeneralRemindersList();
            }

            @Override
            public void OnError(String error, int statusCode) {
                progressStyle(-1);
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        },"crypto","getList","",ReminderList.class);

    }


    public  void onSetViewPager(){
        viewPager = parent.findViewById(R.id.viewPagerForum);
        viewPager.setAdapter(new AdapterPagerReminder(getContext(), this , customeData , generalData));
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.setPageTransformer(true, new PopTransformation());
        if(customeData.size()==0) {
            viewPager.setCurrentItem(0);
            setActiveStyle(true ,params1 ,1 ,  R.attr.whiteBlue  , R.attr.grayWhite2) ;
        } else {
            viewPager.setCurrentItem(1);
            setActiveStyle(false, params, -1, R.attr.grayWhite2, R.attr.whiteBlue);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
           @Override
            public void onPageSelected(int position) {
                if (position == 1)
                    setActiveStyle(false ,params ,-1 ,  R.attr.grayWhite2  , R.attr.whiteBlue) ;
                if (position == 0)
                    setActiveStyle(true ,params1 ,1 ,  R.attr.whiteBlue  , R.attr.grayWhite2) ;
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
        parent.findViewById(R.id.txtRemember).setOnClickListener(view -> {
            viewPager.setCurrentItem(1);
            setActiveStyle(false ,params ,-1 ,  R.attr.grayWhite2  , R.attr.whiteBlue) ;
        });
        parent.findViewById(R.id.txtDay).setOnClickListener(view -> {
            viewPager.setCurrentItem(0);
            setActiveStyle(true ,params1 ,1 ,  R.attr.whiteBlue  , R.attr.grayWhite2) ;
        });
    }

    RelativeLayout.LayoutParams params =
            new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    RelativeLayout.LayoutParams params1 =
            new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public  void setActiveStyle(boolean isStart  , RelativeLayout.LayoutParams param  , int toX , int attr1 , int attr2 ){
        if(isStart) {
            param.addRule(RelativeLayout.START_OF, R.id.imgCenter);
        }
        else {
            param.addRule(RelativeLayout.END_OF, R.id.imgCenter);
        }
        param.setMargins((int) MainActivity.getGlobal().getResources().getDimension(R.dimen._4sdp),
                (int) MainActivity.getGlobal().getResources().getDimension(R.dimen._4sdp),
                (int) MainActivity.getGlobal().getResources().getDimension(R.dimen._4sdp),
                (int) MainActivity.getGlobal().getResources().getDimension(R.dimen._4sdp));
        parent.findViewById(R.id.txtWalk).setLayoutParams(param);
        mAnimation.myTransInLeft1(parent.findViewById(R.id.txtWalk) , 0 , 200 , toX);
        ((TextView)parent.findViewById(R.id.txtDay))     .setTextColor     (new Setting().setColorWithAttrs(getContext() , attr1 ));
        ((TextView)parent.findViewById(R.id.txtRemember)).setTextColor(new Setting().setColorWithAttrs(getContext() , attr2));
    }


    public void mBackPressed() {
        super.mBackPressed();
        MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ButtonWithListeners(true, "", "Are you sure ?", "Exit",
                "ReView", v -> {

                    mAnimation.PressClick(v);
                    MainActivity.getGlobal().closeApp();
                }, v -> {
                    mAnimation.PressClick(v);
                    MainActivity.getGlobal().showReview(FragReminder.this);
                }, "#FF0000", R.drawable.red_alert).setBackOKBtn(R.drawable.shape_redbtn).setBackCancelBtn(R.drawable.shape_btn_cancel));
    }

    public void setLockViewPager (boolean lock) {
        if (lock){
            viewPager.setAllowedSwipeDirection(WrapViewPager.SwipeDirection.none);
        }
        else {
            viewPager.setAllowedSwipeDirection(WrapViewPager.SwipeDirection.all);
        }
    }

    public void onRefreshGeneralRemindersList() {
        List<ReminderData> list=new ArrayList<>();
        list.addAll(generalData);
        list.addAll(customeData);
        String date = new DateManager().getTodayDate( false, false,"");

        for (int i = 0; i < list.size(); i++) {
            if(new Setting().CompareTwoDateMiladi(date.replace("/","-"),list.get(i).getAlertDate().replace("/","-"))==1){
                if(generalData.contains(list.get(i))){
                    generalData.get(generalData.indexOf(list.get(i))).setFlag(3);
                    list.get(i).setFlag(3);
                }
                if(customeData.contains(list.get(i))){
                    customeData.get(customeData.indexOf(list.get(i))).setFlag(3);
                    list.get(i).setFlag(3);
                }
                Updateitem(list.get(i));
                //new SaveInDB(this , "").ReminderSaveInDB(list.get(i), list.get(i).getType(), true);
            }
            if(new Setting().CompareTwoDateMiladi(date.replace("/","-"),list.get(i).getAlertDate().replace("/","-"))==0){
                String time=MainActivity.getGlobal().getTimeCurrent();
                int hour=Integer.parseInt(list.get(i).getAlertHour().split(":")[0]),currenthour=Integer.parseInt(time.split(":")[0]),min=Integer.parseInt(list.get(i).getAlertHour().split(":")[1]),currentmin=Integer.parseInt(time.split(":")[1]);
                if(hour<currenthour || (hour<=currenthour && min<currentmin)) {
                    if (generalData.contains(list.get(i))) {
                        generalData.get(generalData.indexOf(list.get(i))).setFlag(3);
                        list.get(i).setFlag(3);
                    }
                    if (customeData.contains(list.get(i))) {
                        customeData.get(customeData.indexOf(list.get(i))).setFlag(3);
                        list.get(i).setFlag(3);
                    }
                    //new SaveInDB(this , "").ReminderSaveInDB(list.get(i), list.get(i).getType(), true);
                    Updateitem(list.get(i));
                }
            }
        }
        onSetViewPager();


    }

    public void Updateitem(ReminderData reminderData){
        Presenter.get_global().PostAction(new IView<ResponseObject>() {
            @Override
            public void SendRequest() { }

            @Override
            public void OnSucceed(ResponseObject object) { }

            @Override
            public void OnError(String error, int statusCode) { }
        }, "crypto", "updateItem", "", reminderData, ResponseObject.class);
    }

    void progressStyle(int data) {
        //0 -> send Request
        //1 ->success
        //-1 -> failed
        if(data==0){
            parent.findViewById(R.id.relProgress).setVisibility(View.VISIBLE);
            parent.findViewById(R.id.progress).setVisibility(View.VISIBLE);
            parent.findViewById(R.id.btnRtry).setVisibility(View.GONE);
        } else if(data==-1){
            parent.findViewById(R.id.progress).setVisibility(View.GONE);
            parent.findViewById(R.id.btnRtry).setVisibility(View.VISIBLE);
            parent.findViewById(R.id.btnRtry).setOnClickListener(v -> {
                mAnimation.PressClick(v);
                getList();
            });
        }else {
            parent.findViewById(R.id.relProgress).setVisibility(View.GONE);
            parent.findViewById(R.id.progress).setVisibility(View.GONE);
            parent.findViewById(R.id.btnRtry).setVisibility(View.GONE);
        }

    }


}
