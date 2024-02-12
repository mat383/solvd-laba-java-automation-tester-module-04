package com.solvd.laba.testing.api.method;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/posts", methodType = HttpMethodType.POST)
@ResponseTemplatePath(path = "api/posts/_post/create_post_rs.ftl")
@RequestTemplatePath(path = "api/posts/_post/create_post_rq.ftl")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class CreatePost extends AbstractApiMethodV2 {
    public CreatePost() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
