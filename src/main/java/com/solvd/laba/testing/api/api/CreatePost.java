package com.solvd.laba.testing.api.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/posts", methodType = HttpMethodType.POST)
@ResponseTemplatePath(path = "api/posts/create_post_rs.json")
@RequestTemplatePath(path = "api/posts/create_post_rq.json")
public class CreatePost extends AbstractApiMethodV2 {
    public CreatePost() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
