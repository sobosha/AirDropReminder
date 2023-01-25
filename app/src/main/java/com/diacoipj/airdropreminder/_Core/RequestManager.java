package com.diacoipj.airdropreminder._Core;
import android.content.Context;

import com.diacoipj.airdropreminder.Setting.mLocalData;
import com.diacoipj.airdropreminder.Setting.nValue;

import com.diacoipj.airdropreminder._Core.respons.DR1.DataModelResponse;

public class RequestManager {
    Context context ;
    public RequestManager(Context context ) {
        this.context = context ;


    }
    updateMyData updateMyData ;
    public RequestManager (Context context , updateMyData updateMyData) {
        this.context = context ;
        this.updateMyData = updateMyData ;
    }

    public  void onGetData(  String controller  , String action , ReqResult reqResult ){
        Presenter.get_global().GetAction(new IView<DataModelResponse>() {
            @Override
            public void SendRequest() {
            }
            @Override
            public void OnSucceed(DataModelResponse object) {
                reqResult.onSucceed(object);
            }

            @Override
            public void OnError(String error, int statusCode) {
                reqResult.onError(statusCode);
                reqResult.onError();
            }
        } ,controller , action ,"",DataModelResponse.class);

    }

    public  void Logg ( boolean isLog , ReqResult reqResult ){
        String action ;
        if(isLog) action = "install" ; else action = "launch" ;
        Presenter.get_global().GetAction(new IView<String>() {
            @Override
            public void SendRequest() { }

            @Override
            public void OnSucceed(String object) {
                mLocalData.setLogInstall(context,true);
                reqResult.onSucceed(null);
                if (action.equals("launch"))
                    nValue.getGlobal().setLaunched(true);
            }

            @Override
            public void OnError(String error, int statusCode) {
                reqResult.onError(statusCode);
                reqResult.onError();
            }
        } ,"log" , action ,"",String.class);

    }
}
