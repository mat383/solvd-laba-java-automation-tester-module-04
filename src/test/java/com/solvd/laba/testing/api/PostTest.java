package com.solvd.laba.testing.api;

import com.solvd.laba.testing.api.api.CreatePost;
import com.solvd.laba.testing.api.api.GetAllPosts;
import com.solvd.laba.testing.api.api.GetPostById;
import com.solvd.laba.testing.api.domain.Post;
import com.solvd.laba.testing.api.domain.User;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostTest {

    @DataProvider
    public Object[][] provideDataForCreatingPosts() {
        return new Object[][]{
                {1, "Random Title", "Some veeeeeeeeeeeeeeeeeeeeery long body"},
                {1, null, "Some veeeeeeeeeeeeeeeeeeeeery long body"}
        };
    }


    @Test(description = "verify getting existing post by id")
    public void verifyGetPostByValidId() {
        Post post = new Post();
        post.setId(1);

        GetPostById getPostById = new GetPostById(post.getId());
        getPostById.addProperty("post", post);

        getPostById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getPostById.callAPI();

        getPostById.validateResponse();
    }

    @Test(description = "verify that list of posts matches schema")
    public void verifyGetAllPostsMatchesSchemaTest() {
        GetAllPosts getAllPosts = new GetAllPosts();

        getAllPosts.expectResponseStatus(HttpResponseStatusType.OK_200);
        getAllPosts.callAPI();

        getAllPosts.validateResponseAgainstSchema("api/posts/get_posts_rs.schema");
    }

    @Test(dataProvider = "provideDataForCreatingPosts")
    public void verifyCreatePostTest(Integer userId, String title, String body) {
        // create test data
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);

        // create request
        CreatePost createPost = new CreatePost();
        createPost.addProperty("post", post);

        // run request
        createPost.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        createPost.callAPI();

        //validate
        createPost.validateResponse();
    }
}
