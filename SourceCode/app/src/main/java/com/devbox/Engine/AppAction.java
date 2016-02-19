package com.devbox.Engine;

import com.devbox.Entity.CodeLib;
import com.devbox.Entity.CodeType;
import com.devbox.Entity.User;

import java.util.ArrayList;

/**
 * @Author: PandaApe.
 * @CreatedAt: 7/2/16 15:13.
 * @Email: whailong2010@gmail.com
 */
public abstract class AppAction {

    // Web
    public void getLibList(String typeStr, int currentPage, HttpCallback<ArrayList<CodeLib>> callback) {
    }

    public void getTypeList(HttpCallback<ArrayList<CodeType>> callback) {
    }

    public void getLastCommitInfo(String gitHubAddress, GetLastCommitInfoCallback callback) {
    }

    public void loginWithUserNameAndPassword(String userName, String passwor, HttpCallback<User> callback) {
    }


}
