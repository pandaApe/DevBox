package com.devbox.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.devbox.R;

import java.util.HashMap;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.btnQQLogin)
    Button btnQQLogin;
    @Bind(R.id.btnWeiboLogin)
    Button btnWeiboLogin;
    @Bind(R.id.btnWeChatLogin)
    Button btnWeChatLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AVUser.loginBySMSCodeInBackground("", "", new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {

            }
        });

    }

    @OnClick(R.id.btnQQLogin)
    public void viewClicked(View view) {
        Platform qqPlatform = ShareSDK.getPlatform(QQ.NAME);
        qqPlatform.SSOSetting(false);
        qqPlatform.showUser(null);
        qqPlatform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e("tag", "onComplete-->" + i);


                //第二种方法：
                if (hashMap != null && i == 8) {
                    Iterator itor = hashMap.keySet().iterator();

                    while (itor.hasNext()) {
                        String key = (String) itor.next();

                        Log.e("tag", key + "-->" + hashMap.get(key));

                    }
                }


                Log.e("getUserId--->", platform.getDb().getUserId());
                Log.e("getToken--->", platform.getDb().getToken());
                Log.e("getExpiresTime--->", "" + platform.getDb().getExpiresTime());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("tag", "onError-->" + throwable.toString());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("tag", "onCancel");
            }
        });

        qqPlatform.authorize();
//移除授权
//weibo.removeAccount(true);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}

