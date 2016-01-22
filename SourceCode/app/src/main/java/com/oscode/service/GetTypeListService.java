package com.oscode.service;

import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.oscode.model.OSCodeType;

/**
 * Created by whailong on 22/1/16.
 */
public class GetTypeListService {

    public void doGetTypeListQueryWithCompletion(FindCallback<OSCodeType> callBack) {
        AVQuery<OSCodeType> query = AVQuery.getQuery(OSCodeType.class);
        query.findInBackground(callBack);
    }


}
