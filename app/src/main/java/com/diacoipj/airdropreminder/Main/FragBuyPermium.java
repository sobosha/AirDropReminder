package com.diacoipj.airdropreminder.Main;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.CustomClasses.CustomFragment;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;

public class FragBuyPermium extends CustomFragment {
    TextView WalletAddress,Send,VipText;
    RelativeLayout Copy;
    ProgressBar progressBar;
    EditText hash;
    @Override
    public int layout() {
        return R.layout.frag_buy_permium;
    }

    @Override
    public void onCreateMyView() {
        setView();
        WalletAddress.setText(MainActivity.getGlobal().WalletAddress);
        VipText.setText(MainActivity.getGlobal().Viptxt);
        Copy.setOnClickListener(v -> Setting.CopyTextInClipBoard(requireContext(),WalletAddress.getText().toString()));
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter.get_global().PostAction(new IView<ResponseObject>() {
                    @Override
                    public void SendRequest() {

                    }

                    @Override
                    public void OnSucceed(ResponseObject object) {
                        if(object.success)
                            MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());

                    }

                    @Override
                    public void OnError(String error, int statusCode) {

                    }
                },"crypto","activeVip","",new hashCode(hash.getText().toString()),ResponseObject.class);
            }
        });
    }

    private void setView() {
        WalletAddress=parent.findViewById(R.id.TextWallet);
        Copy=parent.findViewById(R.id.WalletAddress);
        Send=parent.findViewById(R.id.BtnNext);
        progressBar=parent.findViewById(R.id.progress);
        VipText=parent.findViewById(R.id.VipText);
        hash=parent.findViewById(R.id.EditTextHash);
    }

    @Override
    public void mBackPressed() {
        super.mBackPressed();
        MainActivity.getGlobal().FinishFragStartFrag(new FragReminder());
    }
}
