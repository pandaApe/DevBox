package com.pa.devbox.domain.service;

import com.pa.devbox.domain.entity.BaseResponse;
import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.domain.entity.rest.CountIncrement;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description:
 * <p>
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
    Observable<BaseResponse<Library>> getLibrary(@QueryMap Map<String, String> parametersMap);

    @PUT("classes/Library/{objId}")
    Observable<ResponseBody> increaseCount(@Path("objId") String objId, @Body CountIncrement countIncrement);

/*
curl -X PUT \
  -H "X-LC-Id: OOhkF87ffBYBHmvph465ApmV-gzGzoHsz" \
  -H "X-LC-Key: OkaU2qxpo1fbHPtc9o7yQVgM" \
  -H "Content-Type: application/json" \
  -d '{"upvotes":{"__op":"Increment","amount":1}}' \
  https://api.leancloud.cn/1.1/classes/Post/558e20cbe4b060308e3eb36c
*/
    
}