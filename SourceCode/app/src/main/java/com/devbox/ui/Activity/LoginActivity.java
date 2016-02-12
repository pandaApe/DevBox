package com.devbox.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.devbox.R;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.btnQQLogin)
    Button btnQQLogin;
    @Bind(R.id.btnWeiboLogin)
    Button btnWeiboLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
    }

    @OnClick(R.id.btnQQLogin)
    public void viewClicked(View view) {
        int viewId = view.getId();

        Platform platform = null;
        String snsType = null;
        if (viewId == R.id.btnQQLogin) {
            platform = ShareSDK.getPlatform(QQ.NAME);
            snsType = AVUser.AVThirdPartyUserAuth.SNS_TENCENT_WEIBO;

        } else {
            platform = ShareSDK.getPlatform(SinaWeibo.NAME);
            snsType = AVUser.AVThirdPartyUserAuth.SNS_SINA_WEIBO;

        }

        String userId = platform.getDb().getUserId();
        if (!TextUtils.isEmpty(userId)) {
            doLogin(platform, snsType);

        } else {

            platform.SSOSetting(false);
            platform.showUser(null);
            final String finalSnsType = snsType;
            platform.setPlatformActionListener(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    if (i == Platform.ACTION_USER_INFOR)
                        doLogin(platform, finalSnsType);


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

            platform.authorize();
        }

    }

    private void doLogin(final Platform platform, String snsType) {
        //绑定第三方的授权信息
        AVUser.AVThirdPartyUserAuth auth =
                new AVUser.AVThirdPartyUserAuth(platform.getDb().getToken(), String.valueOf(platform.getDb()
                        .getExpiresTime()), snsType, platform.getDb()
                        .getUserId());
        AVUser.loginWithAuthData(auth, new LogInCallback<AVUser>() {

            @Override
            public void done(AVUser user, AVException e) {
                if (e == null) {
                    Log.i("---->", "恭喜你，已经和我们的 AVUser 绑定成功");

                    user.put("nickName", platform.getDb().getUserName());
                    user.saveInBackground();


                } else {
                    e.printStackTrace();
                }
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}

