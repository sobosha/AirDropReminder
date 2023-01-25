package com.diacoipj.airdropreminder.Setting;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


import java.io.File;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class nDownloadManager {
    private int downloadStatus;

    public void DownloadMP3(Context context, String URL, String pathFile, String fileName, int positionOfList) {

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(URL.replace(" " , "")));
            request.allowScanningByMediaScanner();
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            File root = new File(pathFile);
            Uri path = Uri.withAppendedPath(Uri.fromFile(root), fileName);
            request.setDestinationUri(path);
            final DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            final long downloadId = manager.enqueue(request);

            Observable<Integer> observable = Observable.create(emitter -> {
                Log.e("MyThreadName", Thread.currentThread().getName());
                boolean downloading = true;
                while (downloading) {
                    DownloadManager.Query q = new DownloadManager.Query();
                    q.setFilterById(downloadId);
                    Cursor cursor = manager.query(q);
                    cursor.moveToFirst();
                    double bytes_downloaded = cursor.getDouble(cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    double bytes_total = cursor.getDouble(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false;
                    }
                    downloadStatus = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));

                    final double dl_progress = (int) ((bytes_downloaded * 100) / bytes_total);
                    if (!emitter.isDisposed()) {
                        emitter.onNext((int) dl_progress);
                        if (dl_progress == 100) {
                            emitter.onComplete();
                        }
                    }
                    cursor.close();
                }
            });
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) { compositeDisposable.add(d); }

                        @Override
                        public void onNext(@NonNull Integer dl_progress) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) { }

                        @Override
                        public void onComplete() {
                            compositeDisposable.clear();
                        }
                    });


        } catch (Exception e) { e.getMessage(); }
    }
}
