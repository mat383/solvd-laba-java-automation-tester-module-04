package com.solvd.laba.testing.api.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${config.api_url}/posts/${post_id}", methodType = HttpMethodType.PATCH)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
@RequestTemplatePath(path = "api/posts/_patch/modify_post_rq.ftl")
@ResponseTemplatePath(path = "api/posts/_patch/modify_post_rs.ftl")
public class ModifyPost extends AbstractApiMethodV2 {
    public ModifyPost(int postId) {
        replaceUrlPlaceholder("post_id", Integer.toString(postId));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
