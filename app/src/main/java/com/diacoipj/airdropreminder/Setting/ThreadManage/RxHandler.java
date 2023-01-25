package com.diacoipj.airdropreminder.Setting.ThreadManage;


import android.util.Log;

import com.diacoipj.airdropreminder.Setting.ThreadManage.RxJavaCallBack.ErrorAnswer;
import com.diacoipj.airdropreminder.Setting.ThreadManage.RxJavaCallBack.RxCallable;
import com.diacoipj.airdropreminder.Setting.ThreadManage.RxJavaCallBack.SuccessAnswer;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RxHandler {
    private static RxHandler global;

    public static void setGlobal(RxHandler global) {
        RxHandler.global = global;
    }

    public static RxHandler getGlobal() {
        if (global == null)
            return new RxHandler();
        return global;
    }



    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public <T> void mFromCallable(Scheduler scheduler, RxCallable<T> rxCallable, SuccessAnswer<T> callbackAnswer, ErrorAnswer errorAnswer) {
        Observable<T> ob = Observable.fromCallable(() -> {
            Log.e("aaaaaaaaaaaaaa" , Thread.currentThread().getName());
            return rxCallable.Object();
        }).subscribeOn(scheduler==null? Schedulers.io():scheduler)
                .observeOn(AndroidSchedulers.mainThread());


        ob.subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull T t) {
                if (callbackAnswer!=null)
                callbackAnswer.Success(t);
                Log.e("aaaaaaaaaaaaaa" , Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if(errorAnswer!=null)
                    errorAnswer.Error(e.getMessage());
            }

            @Override
            public void onComplete() {
                compositeDisposable.clear();
            }
        });
    }

    public<T> void Debounce (Scheduler scheduler, RxCallable<T> rxCallable, SuccessAnswer<T> callbackAnswer, ErrorAnswer errorAnswer , int second) {
        Observable<T> observable = Observable.fromCallable(rxCallable::Object)
                .debounce(second , TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler == null ? Schedulers.io() : scheduler);

        observable.subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull T t) {
                if (callbackAnswer!=null)
                    callbackAnswer.Success(t);
                Log.e("aaaaaaaaaaaaaa" , Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if(errorAnswer!=null)
                    errorAnswer.Error(e.getMessage());
            }

            @Override
            public void onComplete() {
                compositeDisposable.clear();
            }
        });
    }


    /*----------------------------------------------------------------------------------------------------------------------------*/
    public static final class Builder<dataDetection> {

        RxCallable<dataDetection> rxCallable;
        SuccessAnswer<dataDetection> successAnswer;
        ErrorAnswer errorAnswer;
        Scheduler scheduler;
        public Builder<dataDetection> setCallable(RxCallable<dataDetection> rxCallable) {
            this.rxCallable = rxCallable;
            return this;
        }

        public Builder<dataDetection> setSuccess(SuccessAnswer<dataDetection> successAnswer) {
            this.successAnswer = successAnswer;
            return this;
        }

        public Builder<dataDetection> setError(ErrorAnswer errorAnswer) {
            this.errorAnswer = errorAnswer;
            return this;
        }


        public Builder<dataDetection> setScheduler(Scheduler scheduler) {
            this.scheduler = scheduler;
            return this;
        }


        /*------------------------------------------------------- access ----------------------------------------------------------*/
        public RxHandler buildFromCallable(){
            RxHandler.getGlobal().mFromCallable(scheduler , rxCallable, successAnswer , errorAnswer );
            return RxHandler.getGlobal();
        }
        /*------------------------------------------------------- access ----------------------------------------------------------*/
        public RxHandler buildFromCallableWithDebounce(int second){
            RxHandler.getGlobal().Debounce(scheduler , rxCallable, successAnswer , errorAnswer , second);
            return RxHandler.getGlobal();
        }
        /*------------------------------------------------------- access ----------------------------------------------------------*/
    }

}
