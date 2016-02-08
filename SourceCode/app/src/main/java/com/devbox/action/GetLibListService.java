package com.devbox.action;

import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.devbox.model.CodeLib;

/**
 * Created by whailong on 23/1/16.
 */
public class GetLibListService {

    public void doGetLibListQueryWithCompletion(FindCallback<CodeLib> callback) {
        AVQuery<CodeLib> query = AVQuery.getQuery(CodeLib.class);
        query.addDescendingOrder("createdAt");
        query.findInBackground(callback);


    }
}
