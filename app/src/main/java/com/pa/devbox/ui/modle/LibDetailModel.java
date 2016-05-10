package com.pa.devbox.ui.modle;

import com.pa.devbox.domain.FileDownloadHelper.body.ProgressResponseListener;
import com.pa.devbox.domain.RetrofitClient;
import com.pa.devbox.domain.delegate.FileDownloadCallback;
import com.pa.devbox.domain.service.FileDownloadService;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public LibDetailModel() {
        downloadService = RetrofitClient
                .createDownloadService(FileDownloadService.class, this);
    }

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
