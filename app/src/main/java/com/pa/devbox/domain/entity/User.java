package com.pa.devbox.domain.entity;


/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 11/2/16 00:18.
 * Email: whailong2010@gmail.com
 */
public class User {

    private String objectId;
    private String nickName;
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
