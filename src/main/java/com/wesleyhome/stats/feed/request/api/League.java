package com.wesleyhome.stats.feed.request.api;

//import com.wesleyhome.stats.feed.request.api.builder.MLB;
//import com.wesleyhome.stats.feed.request.api.builder.NBA;
//import com.wesleyhome.stats.feed.request.api.builder.NFL;
//import com.wesleyhome.stats.feed.request.api.builder.NHL;

public interface League {

//    League NHL = new NHL();
//    League NFL = new NFL();
//    League NBA = new NBA();
//    League MLB = new MLB();

    boolean isMultiYear();

    String getPeriodName();
}
