package com.hl.devbox.Engine;

import com.hl.devbox.Entity.CodeLib;
import com.hl.devbox.Entity.CodeType;
import com.hl.devbox.Entity.User;

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
