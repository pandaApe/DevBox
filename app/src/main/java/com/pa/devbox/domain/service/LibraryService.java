package com.pa.devbox.domain.service;

import com.pa.devbox.domain.entity.Library;

import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 28/4/16 23:03.
 * Email: whailong2010@gmail.com
 */
public interface LibraryService {

    /*

    1.Search--Keyword
    2.Get List
        A.Order by created time
        B.Refresh and load next page
    3.Get specific one

     */


    @GET("classes/Library")
    Observable<Library> getLibrary();

/*
        //If there is no keyword, it means that must is getting libraries by Type.
        if (!TextUtils.isEmpty(paramMap.get("objId"))) {
            params.put("where", "{\"$relatedTo\":{\"object\":{\"__type\":\"Pointer\",\"className\":\"Type\",\"objectId\":\"" + paramMap.get("objId") + "\"},\"key\":\"libraries\"}}");
            params.put("order", "-createdAt");
//            params.put("limit", "6");
//            params.put("skip", "" + 6 * (currentPage - 1));

        } else if (!TextUtils.isEmpty(paramMap.get("keyword"))) {
            params.put("cql", "select * from Library where name like ? order by createdAt");
            params.put("pvalues", "[\"%" + paramMap.get("keyword") + "%\"]");

        } else {
            params.put("order", "-createdAt");
//            params.put("limit", "6");
//            params.put("skip", "" + 6 * (currentPage - 1));
        }
*/

    @PUT("classes/Library/{objId}")
    Observable increaseCount(@Path("objId") String objId);
    /*

     HttpParams params = new HttpParams();
        params.putHeaders("X-LC-Sign", generateLCSign());
        params.putHeaders("X-LC-Id", BuildConfig.APPId);
        params.putHeaders("Content-Type", "application/json");
        params.putJsonParams("{\"viewCount\":{\"__op\":\"Increment\",\"amount\":1}}");

        Map map = params.getHeaders();
        String requestBody = params.getJsonParams();
        RequestBody body = RequestBody.create(null, params.getJsonParams());
     */

    //Download apk file


}
