package com.devbox.action;

import com.devbox.model.CodeLib;
import com.devbox.model.CodeType;

import java.util.ArrayList;

/**
 * @Author: PandaApe.
 * @CreatedAt: 7/2/16 15:13.
 * @Email: whailong2010@gmail.com
 */
public interface AppAction {


    void getLibList(String typeStr,int currentPage, HttpCallback<ArrayList<CodeLib>> callback);

    void getTypeList(HttpCallback<ArrayList<CodeType>> callback);

    void getLastCommitInfo(String gitHubAddress,GetLastCommitInfoCallback callback);

}
