package com.twtstudio.zhangwenwen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/11/10.
 */

public interface API {
    @GET("news/{type}/page/{page}")
    Call<ListBean> getNewsBean(@Path("type") int type, @Path("page") int page);
    @GET("news/{id}")
    Call<NewsBean> getNewsContent(@Path("id") String number);
}