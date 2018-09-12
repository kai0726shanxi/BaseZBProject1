package com.zsl.test.basezbproject.http;

import com.zsl.test.basezbproject.bean.Banner;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Created by stone
 * @since 2018/7/10
 * 目前只给app设置，用来区分baseurl
 */

public interface APIService {
    /**
     * 测试数据1 get
     * @return
     */
    @Headers({"name:manage"})
    @GET("/api/index/banner_list")
    Observable<HttpResult<List<Banner>>> getBanner();
    /**
     * 测试数据2 post
     * @param parmer
     * @return
     */


    @FormUrlEncoded
    @POST("api/live/live_info")
    Observable<HttpResult<String>> getLiveRoom(@FieldMap Map<String, String> parmer);

}
