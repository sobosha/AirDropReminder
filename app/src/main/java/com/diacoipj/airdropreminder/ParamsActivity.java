package com.diacoipj.airdropreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.Setting.mAnimation;


public class ParamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        if (getIntent().getData().getQueryParameter("status") != null) {
            if (getIntent().getData().getQueryParameter("status").equals("true")) {
                try {

                    if (getIntent().getData().getQueryParameter("type") != null) {
                        if (getIntent().getData().getQueryParameter("type").equals("golden") || getIntent().getData().getQueryParameter("type").equals("silver")) {
                            MainActivity.getGlobal().HideMyRelDialog();
                            MainActivity.getGlobal().HideMessageBox();
                            String desc = getIntent().getData().getQueryParameter("type").equals("golden") ? "عزیزم خرید  "+getIntent().getData().getQueryParameter("quantity")+"  لیلیوم با موفقیت انجام شد \uD83C\uDF38\uD83D\uDE00" : "عزیزم پرداخت با موفقیت انجام شد";
                            MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(MainActivity.getGlobal()).ExitButtonWithClick("success", desc , view -> {
                                mAnimation.PressClick(view);
                                MainActivity.getGlobal().HideMessageBox();
                            }).setTextBtnOk(MainActivity.getGlobal().getResources().getString(R.string.OK)));


                        }
                    } else {
                        MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(MainActivity.getGlobal()).ExitButtonWithClick("error", "عزیزم خریدت "+"انجام نشد ☹️\uD83D\uDC94", view -> {
                            mAnimation.PressClick(view);
                            MainActivity.getGlobal().HideMessageBox();
                        }).setTextBtnOk("متوجه شدم"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(MainActivity.getGlobal()).ExitButtonWithClick("error", "عزیزم خریدت "+"انجام نشد ☹️\uD83D\uDC94", view -> {
                    mAnimation.PressClick(view);
                    MainActivity.getGlobal().HideMessageBox();
                }).setTextBtnOk("متوجه شدم"));
            }

            Intent openMainActivity = new Intent(this, MainActivity.class);
            startActivity(openMainActivity);
        }
    }


}
