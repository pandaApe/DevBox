package com.pa.devbox.ui.modle;

import com.cm.retrofit2.converter.file.body.ProgressResponseListener;
import com.pa.devbox.domain.RetrofitClient;
import com.pa.devbox.domain.delegate.FileDownloadCallback;
import com.pa.devbox.domain.entity.rest.CountIncrement;
import com.pa.devbox.domain.entity.rest.Inner;
import com.pa.devbox.domain.service.FileDownloadService;
import com.pa.devbox.domain.service.LibraryService;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 22:21.
 * Email: whailong2010@gmail.com
 */
public class LibDetailModel implements ProgressResponseListener {

    private FileDownloadService downloadService;
    private FileDownloadCallback fileDownloadCallback;
    private LibraryService libraryService;

    public LibDetailModel(String objId) {

        downloadService = RetrofitClient
                .createDownloadService(FileDownloadService.class, this);

        libraryService = RetrofitClient.shareInstance().create(LibraryService.class);

        increaseViewCount(objId);
    }

    private void increaseViewCount(String objId) {
        CountIncrement increment = new CountIncrement();
        increment.setViewCount(new Inner());
        Observable<ResponseBody> observable = libraryService.increaseCount(objId, increment);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onCompleted() {
//                        Log.e("--->", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.e("--->onError", e.toString());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
//                        Log.e("--->", responseBody.toString());
                    }
                });

    }


    //File download
    public void download(String url, String filePath) {

        Call<File> call = downloadService.download(url, filePath);

        call.enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                if (response.isSuccessful() && response.body() != null)
                    fileDownloadCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<File> call, Throwable t) {
                fileDownloadCallback.onFailure(t);
            }
        });
    }

    @Override
    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
        fileDownloadCallback.onProgress(bytesRead, contentLength, done);
    }

    public void setFileDownloadCallback(FileDownloadCallback fileDownloadCallback) {
        this.fileDownloadCallback = fileDownloadCallback;
    }


}
