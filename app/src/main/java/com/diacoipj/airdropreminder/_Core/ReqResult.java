package com.diacoipj.airdropreminder._Core;

import com.diacoipj.airdropreminder._Core.respons.DR1.DataModelResponse;

public interface ReqResult {
    void onSucceed(DataModelResponse object);
    void onError();
    void onError(int statusCode);
}
