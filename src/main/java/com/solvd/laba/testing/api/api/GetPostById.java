package com.solvd.laba.testing.api.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/posts/${post_id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/posts/get_post_rs.json")
public class GetPostById extends AbstractApiMethodV2 {
    public GetPostById(int postId) {
        replaceUrlPlaceholder("post_id", Integer.toString(postId));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
