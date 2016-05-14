package com.pa.devbox.domain.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 28/4/16 23:15.
 * Email: whailong2010@gmail.com
 */
public interface CommitInfoService {

    @GET("https://api.github.com/repos/{author}/{name}/branches")
    Observable<ResponseBody> getRepositoryInfo(@Path("author") String author, @Path("name") String name);

    @GET("https://api.github.com/repos/{author}/{name}/git/commits/{shaValue}")
    Observable<ResponseBody> getLastCommitInfo(@Path("author") String author, @Path("name") String name, @Path("shaValue") String shaValue);
}
/*
 String[] infoArray = gitHubAddress.split("/");
        final String author = infoArray[infoArray.length - 2];
        final String reposName = infoArray[infoArray.length - 1];

        String reposInfoUrl = BuildConfig.ReposInfoUrl.replace("AUTHOR", author).replace("NAME", reposName);

        new KJHttp().get(reposInfoUrl, new HttpCallBack() {

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                try {
                    JSONArray jsonArray = new JSONArray(t);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.getString("name").equals("master")) {
                            JSONObject insideJsonObj = jsonObject.getJSONObject("commit");
                            String shaValue = insideJsonObj.getString("sha");
                            String requestURL = BuildConfig.LastCommitInfoUrl.replace("AUTHOR", author).replace("NAME", reposName).replace("SHAVALUE", shaValue);
                            new KJHttp().get(requestURL, new HttpCallBack() {

                                @Override
                                public void onFailure(int errorNo, String strMsg) {
                                    super.onFailure(errorNo, strMsg);

                                    if (callback != null) {
                                        callback.done(null, null, null, new AppException(errorNo, strMsg));

                                    }
                                }

                                @Override
                                public void onSuccess(String t) {
                                    super.onSuccess(t);

                                    try {
                                        JSONObject commitJson = new JSONObject(t);

                                        JSONObject commitInfoJson = commitJson.getJSONObject("committer");

                                        String committerName = commitInfoJson.getString("name");
                                        String commitDate = commitInfoJson.getString("date");
                                        String messageStr = commitJson.getString("message");

                                        if (callback != null) {
                                            callback.done(committerName, commitDate, messageStr, null);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                        if (callback != null)
                                            callback.done(null, null, null, new AppException(AppException.INVALID_JSON, "Json解析出错"));

                                    }
                                }
                            });
                        }
                    }




 */