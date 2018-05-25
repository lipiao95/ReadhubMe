package com.lipiao.readhub.me.api;

import com.lipiao.readhub.me.bean.BaseData;
import com.lipiao.readhub.me.bean.HotTopic;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 描述
 */
public interface HotTopicStore {
    @GET("topic")
    Observable<BaseData<HotTopic>> getHotTopic();
}
