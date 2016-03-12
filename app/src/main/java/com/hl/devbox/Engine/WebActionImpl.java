package com.hl.devbox.Engine;

import android.content.Context;
import android.text.TextUtils;

import com.hl.devbox.Entity.Library;

import com.hl.devbox.utils.Config;
import com.hl.devbox.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.CipherUtils;
import org.kymjs.kjframe.utils.SystemTool;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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


    public void getLibList(String typeStr, final int currentPage, final HttpCallback<ArrayList<Library>> callback) {

        if (!checkNet() && callback != null) {
            callback.onFailure(new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            return;
        }

        if (currentPage <= 0 && callback != null) {
            callback.onFailure(new AppException(AppException.PARAM_ILLEGAL, "页码不正确"));
            return;
        }

        if (TextUtils.isEmpty(typeStr)) {

            KJHttp kjHttp = new KJHttp();
            HttpParams params = new HttpParams();

            params.putHeaders("X-LC-Sign", generateLCSign());
            params.putHeaders("X-LC-Id", Config.APPId);
            params.putHeaders("Content-Type", "application/json");
            params.put("order", "createdAt");
            params.put("limit", "6");
            params.put("skip", "" + 6 * (currentPage - 1));

            kjHttp.get(Config.GetLibrariesURL, params, true, new HttpCallBack() {
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    super.onFailure(errorNo, strMsg);
                }

                @Override
                public void onSuccess(String t) {

                    LogUtil.log("onSuccess" + t);

                    super.onSuccess(t);
                }
            });
//
//            AVQuery<Library> query = AVQuery.getQuery(Library.class);
//            query.addDescendingOrder("createdAt");
//
//            query.findInBackground(new FindCallback<CodeLib>() {
//                @Override
//                public void done(List<CodeLib> list, AVException e) {
//                    if (callback != null) {
//                        if (e == null)
//                            callback.onSucess(new ArrayList<>(list), null);
//
//                        else
//                            callback.onSucess(null, new AppException(e.getCode(), e.getMessage()));
//                    }
//                }
//            });
        } else {
//
//            AVQuery<CodeType> query = AVQuery.getQuery(CodeType.class);
//            query.whereEqualTo("typeENDescription", typeStr);
//            query.getFirstInBackground(new GetCallback<CodeType>() {
//                @Override
//                public void done(CodeType codeType, AVException e) {
//
//                    AVQuery<CodeLib> libQuery = codeType.getCodeLibsRelation().getQuery();
//
//                    libQuery.addDescendingOrder("createdAt");
//                    libQuery.limit(10);
//                    libQuery.setSkip((currentPage - 1) * 10);
//
//                    libQuery.findInBackground(new FindCallback<CodeLib>() {
//                        @Override
//                        public void done(List<CodeLib> list, AVException e) {
//                            if (callback != null) {
//                                if (e == null)
//                                    callback.onSucess(new ArrayList<>(list), null);
//                                else
//                                    callback.onSucess(null, new AppException(e.getCode(), e.getMessage()));
//                            }
//                        }
//                    });
//
//
//                }
//            });
        }
    }
//
//    @Override
//    public void getTypeList(final HttpCallback<ArrayList<CodeType>> callback) {
//        if (!checkNet() && callback != null) {
//            callback.onSucess(null, new AppException(AppException.NETWORK_ERROR, "网络未连接"));
//            return;
//        }
//
//        AVQuery<CodeType> query = AVQuery.getQuery(CodeType.class);
//
//        query.findInBackground(new FindCallback<CodeType>() {
//            @Override
//            public void done(List<CodeType> list, AVException e) {
//
//                if (callback != null) {
//                    if (e == null)
//                        callback.onSucess(new ArrayList<>(list), null);
//
//                    else
//                        callback.onSucess(null, new AppException(e.getCode(), e.getMessage()));
//                }
//            }
//        });
//    }

    public void getLastCommitInfo(String gitHubAddress, final GetLastCommitInfoCallback callback) {

        if (!checkNet() && callback != null) {
//            callback.onSucess(null, new AppException(AppException.NETWORK_ERROR, "网络未连接"));
            return;
        }

        if (TextUtils.isEmpty(gitHubAddress) && callback != null) {
//            callback.onSucess(null, new AppException(AppException.PARAM_ILLEGAL, "github 地址不正确"));
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


//    @Override
//    public void loginWithUserNameAndPassword(String userName, String passwor, HttpCallback<User> callback) {
//
//
//    }

}
