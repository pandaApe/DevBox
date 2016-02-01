package com.oscode.service;

import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.oscode.model.CodeType;

/**
 * Created by whailong on 22/1/16.
 */
public class GetTypeListService {

    public void doGetTypeListQueryWithCompletion(FindCallback<CodeType> callBack) {
        AVQuery<CodeType> query = AVQuery.getQuery(CodeType.class);
        query.findInBackground(callBack);
    }
}
