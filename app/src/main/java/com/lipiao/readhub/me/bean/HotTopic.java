package com.lipiao.readhub.me.bean;

import java.util.ArrayList;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 热门话题bean
 */
public class HotTopic {

    public String createdAt;
    public String id;
    public int order;
    public String publishDate;
    public String summary;
    public String title;
    public String updatedAt;
    public Extra extra;
    public ArrayList<News> newsArray;

    @Override
    public String toString() {
        return "HotTopic{" +
                "createdAt='" + createdAt + '\'' +
                ", id='" + id + '\'' +
                ", order=" + order +
                ", publishDate='" + publishDate + '\'' +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", extra=" + extra +
                ", newsArray=" + newsArray +
                '}';
    }

    public static class Extra{
        // 可否即时查看
        boolean instantView;

        @Override
        public String toString() {
            return "Extra{" +
                    "instantView=" + instantView +
                    '}';
        }
    }

}
