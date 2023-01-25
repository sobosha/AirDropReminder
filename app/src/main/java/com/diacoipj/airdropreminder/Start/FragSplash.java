package com.diacoipj.airdropreminder.Start;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diacoipj.airdropreminder.Dialog.RelMessageBox;
import com.diacoipj.airdropreminder.Main.FragLogin;
import com.diacoipj.airdropreminder.Other.Reminders.FragReminder;
import com.diacoipj.airdropreminder.Setting.Setting;
import com.diacoipj.airdropreminder._Core.IView;
import com.diacoipj.airdropreminder._Core.Presenter;
import com.diacoipj.airdropreminder._Core.ReqResult;
import com.diacoipj.airdropreminder._Core.RequestManager;
import com.diacoipj.airdropreminder._Core.login.getNewUser;
import com.diacoipj.airdropreminder._Core.respons.DR1.DataModelResponse;
import com.diacoipj.airdropreminder.MainActivity;
import com.diacoipj.airdropreminder.R;
import com.diacoipj.airdropreminder.Setting.mAnimation;
import com.diacoipj.airdropreminder.Setting.mFragment;
import com.diacoipj.airdropreminder.Setting.mLocalData;

public class FragSplash extends mFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private View parent;

    Setting setting = new Setting();
    RequestManager requestManager ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View parent = inflater.inflate(R.layout.frag_spalsh, container, false);
        this.parent = parent;

        requestManager = new RequestManager(getContext());

        if (!mLocalData.getToken(MainActivity.getGlobal()).equals(""))
            Presenter.get_global().setSERVER_KEY(mLocalData.getToken(MainActivity.getGlobal()));

        OnShowProgress();

        setting.setTypeFace(parent.findViewById(R.id.txtIntro));
        setting.setTypeFace(parent.findViewById(R.id.txtPercent));
        setting.setTypeFace(parent.findViewById(R.id.txtSplash));

        return parent;
    }


    public void OnShowProgress() {

        mAnimation.townScaleMiddle(parent.findViewById(R.id.txtLogo), 0.09f, 0.09f, 1, 1, 0,1000);
        mAnimation.townScale(parent.findViewById(R.id.txtIntro),0,0,1,1,0,1000);
        mAnimation.myTransInLeft(parent.findViewById(R.id.txtSplash),200,1000,-1);

        WhenProgress();

    }

    public void WhenProgress() {

        if (!mLocalData.getLogInstall(parent.getContext())) {
            requestManager.Logg(true, new ReqResult() {
                @Override
                public void onSucceed(DataModelResponse object) {
                    mLocalData.setLogInstall(parent.getContext(), true);
                }
                @Override
                public void onError() { }
                @Override
                public void onError(int statusCode) { }
            });

        }
        if (mLocalData.getToken(parent.getContext()).equals("")) {


            Presenter.get_global().GetAction(new IView<getNewUser>() {
                @Override
                public void SendRequest() { }

                @Override
                public void OnSucceed(getNewUser object) {
                    mLocalData.SetToken(parent.getContext(), object.getData().getToken());
                    requestManager.Logg(false, new ReqResult() {
                        @Override
                        public void onSucceed(DataModelResponse object) {
                            onFinish();
                        }
                        @Override
                        public void onError() {
                            MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext())
                                    .ExitButton("connectionError" ,"please check your internet connection"));
                        }
                        @Override
                        public void onError(int statusCode) { }
                    });
                }

                @Override
                public void OnError(String error, int statusCode) {
                    MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext())
                            .ExitButton("connectionError" ,"please check your internet connection"));
                }
            } , "users", "new" , "" , getNewUser.class);

        } else {
            requestManager.Logg(false, new ReqResult() {
                @Override
                public void onSucceed(DataModelResponse object) {
                    onFinish();
                }
                @Override
                public void onError() {
                    MainActivity.getGlobal().ShowMessageBox(new RelMessageBox(getContext())
                            .ExitButton("connectionError" ,"please check your internet connection"));
                }
                @Override
                public void onError(int statusCode) { }
            });
        }
    }

    /*-------------------------------- finish ------------------------------*/

    private void onFinish()
    {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(mLocalData.getEmail(requireContext()).equals("")){
                    MainActivity.getGlobal().FinishFragStartFrag( new FragLogin());
                }
                else
                    MainActivity.getGlobal().FinishFragStartFrag( new FragReminder());
            }
        },1000);
    }




}
