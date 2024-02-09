package com.solvd.laba.testing.api.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url/posts/1", methodType = HttpMethodType.GET)
public class GetAllPosts extends AbstractApiMethodV2 {
    public GetAllPosts() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
