package com.diacoipj.airdropreminder.Setting.RxjavaCallBack;

import io.reactivex.rxjava3.core.ObservableEmitter;

public interface IEmitObject<T> {
    void emit(ObservableEmitter<T> emitter);
}
