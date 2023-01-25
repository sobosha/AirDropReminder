package com.diacoipj.airdropreminder.Main;

import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomFragment;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.globalResult;
import com.diacoipj.airdropreminder._Core.requset.UserCreate;

public class FragLogin extends CustomFragment {
    TextView Next;
    CheckBox Rules;
    EditText Email;
    @Override
    public int layout() {
        return R.layout.frag_login2;
    }

    @Override
    public void onCreateMyView() {
        setView();
        Next.setOnClickListener(v -> {
            mAnimation.PressClick(v);
            if(Check()){
                SendEmailToServer();
               // MainActivity.getGlobal().FinishFragStartFrag(new FragCheckCode());
            }
        });
    }

    void SendEmailToServer(){
        parent.findViewById(R.id.progress).setVisibility(View.VISIBLE);
        ((TextView)parent.findViewById(R.id.BtnNext)).setText("");
        Presenter.get_global().PostAction(new IView<globalResult>() {
            @Override
            public void SendRequest() { }

            @Override
            public void OnSucceed(globalResult object) {
                parent.findViewById(R.id.progress).setVisibility(View.GONE);
                ((TextView)parent.findViewById(R.id.BtnNext)).setText("Next");
                new Setting().HideKeyBoard();
                if (object.isSuccess() ) {
                    MainActivity.getGlobal().FinishFragStartFrag(new FragmentLogin().login(Email.getText().toString()));
                } else
                    WrongPhone(!Setting.isEnLang()?"مشکلی پیش اومده!":"there is something wrong");
            }
            @Override
            public void OnError(String error, int statusCode) {
                ((TextView)parent.findViewById(R.id.BtnNext)).setText("Next");
                parent.findViewById(R.id.progress).setVisibility(View.GONE);
                MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext()).ExitButton("connectionError",  MainActivity.getGlobal().getResources().getString(R.string.connected1)));
            }
        } , "users" ,"loginV3" , "" ,new UserCreate(Email.getText().toString())  , globalResult.class);
    }

    private void WrongPhone (String wrong) {
        ((TextView)parent.findViewById(R.id.txtWrong)).setText(wrong);
        mAnimation.Viberation(parent.findViewById(R.id.txtWrong) , 20);
        parent.findViewById(R.id.txtWrong).setVisibility(View.VISIBLE);
    }

    private void setView() {

        Next=parent.findViewById(R.id.BtnNext);
        Email=parent.findViewById(R.id.EditTextHash);
        Rules=parent.findViewById(R.id.CheckRule);
        Email.setHint("yourmail@email.com");
        Email.setFilters(new InputFilter[] {new InputFilter.LengthFilter(100)});
        Email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    public boolean Check(){
        if(!Rules.isChecked()){
            mAnimation.Viberation(Rules);
            return false;
        }
        else if(!Setting.isEmailValid(Email.getText().toString())){
            MainActivity.getGlobal().showSnackBar("reject","Wrong email",1000);
            return false;
        }
        else if(!new Setting().isNetworkConnect()){
            MainActivity.getGlobal().showSnackBar("warning","Check your internet connection",1000);
            return false;
        }
        return true;
    }
}
