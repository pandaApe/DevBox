package com.devbox.action;

import android.content.Context;
import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.devbox.model.CodeLib;
import com.devbox.model.CodeType;
import com.devbox.utils.DBConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.utils.SystemTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 9/2/16 19:29.
 * @Email: whailong2010@gmail.com
 */
public class AppActionImpl implements AppAction {

    private Context context;

    public AppActionImpl(Context context) {
        this.context = context;
    }

    private boolean checkNet() {
        return SystemTool.checkNet(this.context);
    }

    @Override
    public void getLibList(String typeStr, int currentPage, final HttpCallback<ArrayList<CodeLib>> callback) {

        if (!checkNet() && callback != null) {
            callback.onFailure(ErrorEvent.NETWORKERROR, "网络未连接");
            return;
        }

        if (currentPage < 0 && callback != null) {
            callback.onFailure(ErrorEvent.PARAM_ILLEGAL, "页码不正确");
            return;
        }






        AVQuery<CodeLib> query = AVQuery.getQuery(CodeLib.class);
        query.addDescendingOrder("createdAt");





        query.findInBackground(new FindCallback<CodeLib>() {
            @Override
            public void done(List<CodeLib> list, AVException e) {
                if (callback != null) {
                    if (e == null)
                        callback.onSuccess(new ArrayList<>(list));
                    else
                        callback.onFailure(ErrorEvent.LEANCLOUDERROR, e.toString());
                }
            }
        });

    }


    @Override
    public void getTypeList(final HttpCallback<ArrayList<CodeType>> callback) {
        if (!checkNet() && callback != null) {
            callback.onFailure(ErrorEvent.NETWORKERROR, "网络未连接");
            return;
        }

        AVQuery<CodeType> query = AVQuery.getQuery(CodeType.class);

        query.findInBackground(new FindCallback<CodeType>() {
            @Override
            public void done(List<CodeType> list, AVException e) {

                if (callback != null) {
                    if (e == null) {
                        callback.onSuccess(new ArrayList<>(list));
                    } else {
                        callback.onFailure(ErrorEvent.LEANCLOUDERROR, e.toString());
                    }
                }
            }
        });
    }

    @Override
    public void getLastCommitInfo(String gitHubAddress, final GetLastCommitInfoCallback callback) {

        if (!checkNet() && callback != null) {
            callback.onFailure(ErrorEvent.NETWORKERROR, "网络未连接");
            return;
        }

        if (TextUtils.isEmpty(gitHubAddress) && callback != null) {
            callback.onFailure(ErrorEvent.PARAM_NULL, "github 地址不正确");
            return;
        }

        String[] infoArray = gitHubAddress.split("/");
        final String author = infoArray[infoArray.length - 2];
        final String reposName = infoArray[infoArray.length - 1];

        String reposInfoUrl = DBConfig.ReposInfoUrl.replace("AUTHOR", author).replace("NAME", reposName);

        new KJHttp().get(reposInfoUrl, new HttpCallBack() {

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
                            String requestURL = DBConfig.LastCommitInfoUrl.replace("AUTHOR", author).replace("NAME", reposName).replace("SHAVALUE", shaValue);
                            new KJHttp().get(requestURL, new HttpCallBack() {

                                @Override
                                public void onFailure(int errorNo, String strMsg) {
                                    super.onFailure(errorNo, strMsg);

                                    if (callback != null) {
                                        callback.onFailure("HTTPERROR-" + errorNo, strMsg);
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
                                            callback.onSuccess(committerName, commitDate, messageStr);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                        if (callback != null) {
                                            callback.onFailure("JSONParseError", "Json解析出错");
                                        }
                                    }
                                }
                            });
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFailure("JSONParseError", "Json解析出错");
                    }
                }

            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);

                if (callback != null) {
                    callback.onFailure("HTTPERROR-" + errorNo, strMsg);
                }
            }
        });

    }
}
