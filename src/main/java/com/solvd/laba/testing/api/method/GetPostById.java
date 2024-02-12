package com.solvd.laba.testing.api.method;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/posts/${post_id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/posts/_get/get_post_rs.ftl")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetPostById extends AbstractApiMethodV2 {
    public GetPostById(int postId) {
        replaceUrlPlaceholder("post_id", Integer.toString(postId));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
