package com.hl.devbox.Entity;

import org.kymjs.kjframe.database.OneToManyLazyLoader;
import org.kymjs.kjframe.database.annotate.Id;
import org.kymjs.kjframe.database.annotate.OneToMany;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 11/2/16 00:18.
 * @Email: whailong2010@gmail.com
 */
public class User {

    @Id
    private String objectId;
    private String nickName;
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    @OneToMany(manyColumn = "liked")
    private transient OneToManyLazyLoader<User, Library> likedLibs;

    public OneToManyLazyLoader<User, Library> getLikedLibs() {
        return likedLibs;
    }

    public void setLikedLibs(OneToManyLazyLoader<User, Library> likedLibs) {
        this.likedLibs = likedLibs;
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
