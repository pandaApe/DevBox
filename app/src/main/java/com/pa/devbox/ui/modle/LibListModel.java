package com.pa.devbox.ui.modle;

import com.pa.devbox.domain.RetrofitClient;
import com.pa.devbox.domain.delegate.HttpRequestCallback;
import com.pa.devbox.domain.entity.BaseResponse;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.domain.service.LibraryService;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 10:01.
 * Email: whailong2010@gmail.com
 */
public class LibListModel {

    private LibraryService libraryService;

    private HttpRequestCallback<Library> callback;

    public LibListModel() {
        libraryService = RetrofitClient.shareInstance().create(LibraryService.class);
    }


    public void getLibsByType(String typeId, int currentPageIndex) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("where", "{\"$relatedTo\":" +
                "{\"object\":" +
                "{\"__type\":\"Pointer\",\"className\":\"Type\",\"objectId\":\"" + typeId + "\"" +
                "}," +
                "\"key\":\"libraries\"" +
                "}" +
                "}");
        paramsMap.put("order", "-createdAt");
        paramsMap.put("limit", "10");
        paramsMap.put("skip", "" + 10 * (currentPageIndex - 1));

        request(paramsMap);
    }

    public void getLibsBySearch(String keyword, int currentPageIndex) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("cql", "select * from Library where name like ? order by createdAt");
        paramsMap.put("pvalues", "[\"%" + keyword + "%\"]");
        request(paramsMap);
    }

    public void getLibs(int currentPageIndex) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("order", "-createdAt");
        paramsMap.put("limit", "10");
        paramsMap.put("skip", "" + 10 * (currentPageIndex - 1));

        request(paramsMap);
    }


    private void request(Map<String, String> paramStr) {
        Observable<BaseResponse<Library>> observable = libraryService.getLibrary(paramStr);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<Library>>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onNext(BaseResponse<Library> response) {
                        callback.onSuccess(response.getResults());
                    }
                });
    }

    public void setCallback(HttpRequestCallback<Library> callback) {
        this.callback = callback;
    }
}
