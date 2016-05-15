package com.pa.devbox.ui.modle;

import android.util.Log;

import com.cm.retrofit2.converter.file.body.ProgressResponseListener;
import com.pa.devbox.domain.RetrofitClient;
import com.pa.devbox.domain.delegate.FileDownloadCallback;
import com.pa.devbox.domain.delegate.LastCommitInfoCallback;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.domain.entity.rest.Branch;
import com.pa.devbox.domain.entity.rest.CountIncrement;
import com.pa.devbox.domain.entity.rest.Inner;
import com.pa.devbox.domain.entity.rest.LastCommitInfo;
import com.pa.devbox.domain.service.CommitInfoService;
import com.pa.devbox.domain.service.FileDownloadService;
import com.pa.devbox.domain.service.LibraryService;

import java.io.File;
import java.util.List;

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

    private LastCommitInfoCallback lastCommitInfoCallback;

    private CommitInfoService commitInfoService;

    private LibraryService libraryService;
    private Library library;

    public LibDetailModel(Library library) {

        downloadService = RetrofitClient
                .createDownloadService(FileDownloadService.class, this);

        libraryService = RetrofitClient.shareInstance().create(LibraryService.class);
        CountIncrement increment = new CountIncrement();

        this.library = library;
        increment.setViewCount(new Inner());
        increaseCount(increment);

        commitInfoService = RetrofitClient.shareInstance().create(CommitInfoService.class);

        getCommitInfo();
    }

    private void getCommitInfo() {

        String[] infoArray = this.library.getGithubAddress().split("/");
        final String author = infoArray[infoArray.length - 2];
        final String reposName = infoArray[infoArray.length - 1];

        Observable<List<Branch>> observable = commitInfoService.getRepositoryInfo(author, reposName);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Branch>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Branch> branches) {
                        Log.e("------->", "" + branches.size());

                        String shaValue = "";

                        for (Branch item : branches) {
                            if (item.getName().equals("master")) {
                                shaValue = item.getCommit().getSha();
                                break;
                            }
                        }

                        Observable<LastCommitInfo> observable = commitInfoService.getLastCommitInfo(author, reposName, shaValue);
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<LastCommitInfo>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(LastCommitInfo lastCommitInfo) {
                                        String msg = lastCommitInfo.getCommitter().getName() + ": " + lastCommitInfo.getMessage();

                                        lastCommitInfoCallback.onSuccess(lastCommitInfo.getCommitter().getDate(), msg);
                                    }
                                });
                    }
                });
    }

    private void increaseCount(CountIncrement increment) {

        Observable<ResponseBody> observable = libraryService.increaseCount(this.library.getObjectId(), increment);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();

    }

    //File download
    public void download(String url, String filePath) {

        Call<File> call = downloadService.download(url, filePath);

        call.enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fileDownloadCallback.onSuccess(response.body());
                    CountIncrement increment = new CountIncrement();
                    increment.setDownloadCount(new Inner());
                    increaseCount(increment);

                }
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

    public void setLastCommitInfoCallback(LastCommitInfoCallback lastCommitInfoCallback) {
        this.lastCommitInfoCallback = lastCommitInfoCallback;
    }

}
