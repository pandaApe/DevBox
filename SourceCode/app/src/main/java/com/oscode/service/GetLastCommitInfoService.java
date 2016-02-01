package com.oscode.service;

import android.util.Log;

import com.oscode.model.CodeLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;

/**
 * Created by whailong on 30/1/16.
 */
public class GetLastCommitInfoService {
    private CodeLib codeLib;

    public void setCodeLib(CodeLib codeLib) {
        this.codeLib = codeLib;
    }

    public void getGetLastCommitInfoWithCompletgion(final GetLastCommitCallBack callBack) {


//https://api.github.com/repos/rg3/youtube-dl/branches


        String requestURL = "https://api.github.com/repos/" + codeLib.getAuthor() + "/" + codeLib.getLibName() + "/branches";

//https://api.github.com/repos/rg3/youtube-dl/git/commits/b8c9926c0a9d68fb636bf99e873d6a88de41f76b
        new KJHttp().get(requestURL, new HttpCallBack() {
            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);

                callBack.onFailure();
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                try {
                    JSONArray jsonArray = new JSONArray(t);
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.getString("name").equals("master")) {
                            JSONObject insideJsonObj = jsonObject.getJSONObject("commit");
                            String shaValue = insideJsonObj.getString("sha");
                            String requestURL = "https://api.github.com/repos/" + codeLib.getAuthor() + "/" + codeLib.getLibName() + "/git/commits/" + shaValue;
                            new KJHttp().get(requestURL, new HttpCallBack() {


                                @Override
                                public void onFailure(int errorNo, String strMsg) {
                                    super.onFailure(errorNo, strMsg);
                                    callBack.onFailure();
                                }

                                @Override
                                public void onSuccess(String t) {
                                    super.onSuccess(t);
                                    Log.v("---->", t);
                                    try {
                                        JSONObject commitJson = new JSONObject(t);

                                        JSONObject commitInfoJson = commitJson.getJSONObject("committer");

                                        String committerName = commitInfoJson.getString("name");
                                        String commitDate = commitInfoJson.getString("date");
                                        String messageStr = commitJson.getString("message");

                                        callBack.onSuccess(committerName, commitDate, messageStr);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v("---->", t);
            }
        });

    }

    public interface GetLastCommitCallBack {
        void onSuccess(String committerName, String commitDate, String msgStr);

        void onFailure();
    }

}
