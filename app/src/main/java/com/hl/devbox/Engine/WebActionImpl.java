package com.hl.devbox.Engine;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hl.devbox.Entity.Library;
import com.hl.devbox.Entity.Type;

import com.hl.devbox.utils.Config;
import com.hl.devbox.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.CipherUtils;
import org.kymjs.kjframe.utils.SystemTool;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.RequestBody;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 9/2/16 19:29.
 * @Email: whailong2010@gmail.com
 */
public class WebActionImpl extends AppAction {

    private Context context;

    public WebActionImpl(Context context) {
        this.context = context;
    }

    private String generateLCSign() {
        String timeStamp = new Timestamp(new Date().getTime()).toString();

        return CipherUtils.md5(timeStamp + Config.APPKey) + "," + timeStamp;
    }

    private boolean checkNet() {
        return SystemTool.checkNet(this.context);
    }


    public void getLibList(HashMap<String, String> paramMap, final int currentPage, final HttpCallback<List<Library>> callback) {

        if (!checkNet()) {
            if (callback != null)
                callback.onFailure(new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            return;
        }

        if (currentPage <= 0) {
            if (callback != null)
                callback.onFailure(new AppException(AppException.PARAM_ILLEGAL, "页码不正确"));
            return;
        }


        KJHttp kjHttp = new KJHttp();
        HttpParams params = new HttpParams();

        params.putHeaders("X-LC-Sign", generateLCSign());
        params.putHeaders("X-LC-Id", Config.APPId);
        params.putHeaders("Content-Type", "application/json");

        //If there is no keyword, it means that must is getting libraries by Type.
        if (!TextUtils.isEmpty(paramMap.get("objId"))) {
            params.put("where", "{\"$relatedTo\":{\"object\":{\"__type\":\"Pointer\",\"className\":\"Type\",\"objectId\":\"" + paramMap.get("objId") + "\"},\"key\":\"libraries\"}}");
            params.put("order", "-createdAt");
//            params.put("limit", "6");
//            params.put("skip", "" + 6 * (currentPage - 1));

        } else if (!TextUtils.isEmpty(paramMap.get("keyword"))) {
            params.put("cql", "select * from Library where name like ? order by createdAt");
            params.put("pvalues", "[\"%" + paramMap.get("keyword") + "%\"]");

        } else {
            params.put("order", "-createdAt");
//            params.put("limit", "6");
//            params.put("skip", "" + 6 * (currentPage - 1));
        }

        kjHttp.get(Config.GetLibrariesURL, params, true, new HttpCallBack() {
            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }

            @Override
            public void onSuccess(String t) {

                LogUtil.log("onSuccess--->" + t);

                String json = "";
                try {
                    json = new JSONObject(t).getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                List list = gson.fromJson(json, new TypeToken<List<Library>>() {
                }.getType());

                if (callback != null && list != null)
                    callback.onSucess(list);
            }
        });
    }

    public void getTypeList(final HttpCallback<List<Type>> callback) {
        if (!checkNet()) {
            if (callback != null)
                callback.onFailure(new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            return;
        }

        KJHttp kjHttp = new KJHttp();
        HttpParams params = new HttpParams();

        params.putHeaders("X-LC-Sign", generateLCSign());
        params.putHeaders("X-LC-Id", Config.APPId);
        params.putHeaders("Content-Type", "application/json");

        kjHttp.get(Config.GetTypesURL, params, true, new HttpCallBack() {
            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }

            @Override
            public void onSuccess(String t) {

                LogUtil.log("onSuccess--->" + t);

                String json = "";
                try {
                    json = new JSONObject(t).getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                List list = gson.fromJson(json, new TypeToken<List<Type>>() {
                }.getType());

                if (callback != null)
                    callback.onSucess(list);
            }
        });
    }

    public void getLastCommitInfo(String gitHubAddress, final GetLastCommitInfoCallback callback) {

        if (!checkNet()) {
            if (callback != null)
                callback.onFailure(new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            return;
        }

        if (TextUtils.isEmpty(gitHubAddress)) {
            if (callback != null)
                callback.onFailure(new AppException(AppException.PARAM_ILLEGAL, "github 地址不正确"));
            return;
        }

        String[] infoArray = gitHubAddress.split("/");
        final String author = infoArray[infoArray.length - 2];
        final String reposName = infoArray[infoArray.length - 1];

        String reposInfoUrl = Config.ReposInfoUrl.replace("AUTHOR", author).replace("NAME", reposName);

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
                            String requestURL = Config.LastCommitInfoUrl.replace("AUTHOR", author).replace("NAME", reposName).replace("SHAVALUE", shaValue);
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

                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.done(null, null, null, new AppException(AppException.INVALID_JSON, "Json解析出错"));
                    }
                }

            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);

                if (callback != null) {
                    callback.done(null, null, null, new AppException(errorNo, strMsg));
                }
            }
        });
    }

    @Override
    public void downloadApkFile(Library lib, final HttpCallback<String> callback) {
        if (!checkNet()) {
            if (callback != null) {
                callback.onFailure(new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            }
            return;
        }

        if (lib == null) {
            if (callback != null) {
                callback.onFailure(new AppException(AppException.PARAM_NULL, "参数为空"));
            }
            return;
        }

        final String apkName = lib.getName().replace(" ", "") + ".apk";
        OkHttpUtils.get().url(lib.getApk().getUrl()).addHeader("X-LC-Sign", generateLCSign()).addHeader("X-LC-Id", Config.APPId).build()
                .execute(new FileCallBack(Config.AppFolder, apkName) {

                    @Override
                    public void inProgress(float progress, long total) {
                        int pro = (int) (progress * 100);

                        if (callback != null && pro != 0)
                            callback.onProgress(pro);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        LogUtil.log("onFailure--->" + e);
                        if (callback != null)
                            callback.onFailure(new AppException(AppException.CONNECTION_FAILED, "服务器连接失败"));
                    }

                    @Override
                    public void onResponse(File file) {
                        LogUtil.log("onSuccess");
                        if (callback != null) {
                            callback.onSucess(file.getAbsolutePath());
                            callback.onSucess();
                        }
                    }
                });

    }

    @Override
    public void increaseViewCount(String objId, HttpCallback callback) {

        if (!checkNet()) {
            if (callback != null) {
                callback.onFailure(new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            }
            return;
        }

        if (objId == null) {
            if (callback != null) {
                callback.onFailure(new AppException(AppException.PARAM_NULL, "参数为空"));
            }
            return;
        }

        HttpParams params = new HttpParams();
        params.putHeaders("X-LC-Sign", generateLCSign());
        params.putHeaders("X-LC-Id", Config.APPId);
        params.putHeaders("Content-Type", "application/json");
        params.putJsonParams("{\"viewCount\":{\"__op\":\"Increment\",\"amount\":1}}");

        Map map = params.getHeaders();
        String requestBody = params.getJsonParams();
        RequestBody body = RequestBody.create(null, params.getJsonParams());
//        OkHttpUtils.put().headers(params.getHeaders()).requestBody(body).url(Config.GetLibrariesURL + "/" + objId).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                LogUtil.log(response);
//            }
//        });

    }
}
