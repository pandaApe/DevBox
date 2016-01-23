package com.oscode.service;

import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.oscode.model.OSCodeLib;

/**
 * Created by whailong on 23/1/16.
 */
public class GetLibListService {

    public void doGetLibListQueryWithCompletion(FindCallback<OSCodeLib> callback) {
        AVQuery<OSCodeLib> query = AVQuery.getQuery(OSCodeLib.class);
        query.addDescendingOrder("createdAt");
        query.findInBackground(callback);
    }
}
