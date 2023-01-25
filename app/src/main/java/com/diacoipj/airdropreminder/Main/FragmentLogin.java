package com.diacoipj.airdropreminder.Main;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomFragment;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.TimerEvent;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.globalResult;
import com.diacoipj.airdropreminder._Core.requset.UserCreate;
import com.diacoipj.airdropreminder._Core.respons.DR1.DataModelResponse;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class FragmentLogin extends CustomFragment {
    EditText edtLoginCode ;

    TimerEvent timerEvent ;
    long TIME ;
    String email ="" ;

    public FragmentLogin login(String phone) {
        this.email = phone;
        return this ;
    }

    @Override
    public int layout() {
        return R.layout.frag_login;
    }

    @Override
    public void onCreateMyView() {
        ((TextView)parent.findViewById(R.id.txtEmailll)).setText(email);
        parent.findViewById(R.id.txtTimeDes).setVisibility(View.GONE);
        edtLoginCode = parent.findViewById(R.id.edtLoginCode);
        parent.findViewById(R.id.txtAcceptCode).setOnClickListener(view -> {
           if (edtLoginCode.getText().toString().isEmpty()) {
                WrongPhone("enter the code");
            }
            else {
                ((EditText)parent.findViewById(R.id.edtLoginCode)).setMaxEms(7);
                CheckCode();
            }
        });
        parent.findViewById(R.id.txtCancelCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getGlobal().FinishFragStartFrag(new FragLogin());
            }
        });
        CorrectPhone();
    }


    private void CorrectPhone () {
        parent.findViewById(R.id.txtTimeDes).setClickable(false);
        parent.findViewById(R.id.txtTimeDes).setVisibility(View.VISIBLE);
        timerEvent = new TimerEvent() {
            @Override
            public void onTick(String time) {
                ((TextView)parent.findViewById(R.id.txtTimeDes)).setText(time +" to resend the code");
            }
            @Override
            public void onFinish() {
                parent.findViewById(R.id.txtTimeDes).setClickable(true);
                ((TextView)parent.findViewById(R.id.txtTimeDes)).setText("click to resend the code");
                parent.findViewById(R.id.txtTimeDes).setOnClickListener(view -> {
                    mAnimation.PressClick(view);
                    setMobile();
                });
            }
        };

        parent.findViewById(R.id.txtWrong).setVisibility(View.GONE);
        ShowTimer(120);

    }

    void setMobile(){
        Presenter.get_global().PostAction(new IView<globalResult>() {
            @Override
            public void SendRequest() { }

            @Override
            public void OnSucceed(globalResult object) {
                new Setting().HideKeyBoard();
                if (object.isSuccess() ) {
                    MainActivity.getGlobal().FinishFragStartFrag(new FragmentLogin());
                } else
                    WrongPhone(!Setting.isEnLang()?"مشکلی پیش اومده!":"there is something wrong");
            }
            @Override
            public void OnError(String error, int statusCode) {
                MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ExitButton("connectionError",  MainActivity.getGlobal().getResources().getString(R.string.connected1)));
            }
        } , "users" ,"loginV3" , "" ,new UserCreate(email)  , globalResult.class);
    }
    private void WrongPhone (String wrong) {
        ((TextView)parent.findViewById(R.id.txtWrong)).setText(wrong);
        mAnimation.Viberation(parent.findViewById(R.id.txtWrong) , 20);
        parent.findViewById(R.id.txtWrong).setVisibility(View.VISIBLE);
    }

    private void CheckCode () {
        parent.findViewById(R.id.progress).setVisibility(View.VISIBLE);
        ((TextView)parent.findViewById(R.id.txtAcceptCode)).setText("");
        Presenter.get_global().PostAction(new IView<DataModelResponse>() {
            @Override
            public void SendRequest() { }
            @Override
            public void OnSucceed(DataModelResponse object) {
                ((TextView)parent.findViewById(R.id.txtAcceptCode)).setText("Confirm");
                new Setting().HideKeyBoard();
                parent.findViewById(R.id.progress).setVisibility(View.GONE);
                if (object.isSuccess()){
                    handler.removeCallbacksAndMessages(null);
                    if(object.getToken()!=null)
                        mLocalData.SetToken(requireContext(),object.getToken());

                    mLocalData.setEmail(getContext() , email);
                    MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());
                }
                else {
                    WrongPhone("the code is wrong");
                }
            }

            @Override
            public void OnError(String error, int statusCode) {
                ((TextView)parent.findViewById(R.id.txtAcceptCode)).setText("Confirm");
                parent.findViewById(R.id.progress).setVisibility(View.GONE);
                Toast.makeText(getContext(), error+statusCode, Toast.LENGTH_SHORT).show();

            }
        } , "users" , "validate" , "" ,new UserCreate(email,Integer.parseInt(edtLoginCode.getText().toString())) , DataModelResponse.class );
    }
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

}
