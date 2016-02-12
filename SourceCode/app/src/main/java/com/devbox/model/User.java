package com.devbox.model;

import com.avos.avoscloud.AVUser;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 11/2/16 00:18.
 * @Email: whailong2010@gmail.com
 */
public class User extends AVUser {

    private String nickName;

    public String getNickName() {
        return getString("nickName");
    }

    public void setNickName(String nickName) {
        this.put("nickName", nickName);
    }
}
