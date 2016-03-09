package com.hl.devbox.Engine;

import android.graphics.Bitmap;

import com.hl.devbox.Entity.Library;
import com.hl.devbox.Entity.User;

import java.util.ArrayList;

/**
 * @Author: PandaApe.
 * @CreatedAt: 7/2/16 15:13.
 * @Email: whailong2010@gmail.com
 */
public abstract class AppAction {

    // Web
    public void getLibraryList(String typeStr, int currentPage, HttpCallback<ArrayList<Library>> callback) {
    }

    public void increaseViewCount(HttpCallback callback) {
    }

    public void updateCollectionCount(int amount, HttpCallback callback) {
    }

    public void getLibImage(Library lib, HttpCallback<Bitmap> callback) {
    }

    public void getApkFile(Library lib, HttpCallback<byte[]> callback) {
    }

    public void getTypeList(HttpCallback<ArrayList<Library>> callback) {
    }

    public void getLastCommitInfo(String gitHubAddress, GetLastCommitInfoCallback callback) {
    }

    public void login(String userName, String passwor, HttpCallback<User> callback) {
    }


}
