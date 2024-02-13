package com.solvd.laba.testing.api;

import com.solvd.laba.testing.api.domain.Post;
import com.solvd.laba.testing.api.domain.User;
import com.solvd.laba.testing.api.method.CreatePost;
import com.solvd.laba.testing.api.method.GetAllPosts;
import com.solvd.laba.testing.api.method.GetPostById;
import com.solvd.laba.testing.api.method.ModifyPost;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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

        getPostById.callAPIExpectSuccess();

        getPostById.validateResponse();
    }

    @Test(description = "verify that list of posts matches schema")
    public void verifyGetAllPostsMatchesSchemaTest() {
        GetAllPosts getAllPosts = new GetAllPosts();

        getAllPosts.callAPIExpectSuccess();

        getAllPosts.validateResponseAgainstSchema("api/posts/_get/get_posts_rs.schema");
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
        createPost.setProperties("api/posts/post_random.properties");

        // run request
        createPost.callAPIExpectSuccess();

        //validate
        createPost.validateResponse();
    }

    @Test
    public void verifyCreateAndUpdatePostTest() {
        // prepare data for request
        User user = new User();
        user.setId(1);

        Post post = new Post();
        post.setUser(user);
        post.setTitle("Some Strange Title");
        post.setBody("Body body body body body");


        // create post
        CreatePost createPost = new CreatePost();
        createPost.setProperties("api/posts/post_random.properties");

        Response createPostResponse = createPost.callAPIExpectSuccess();
        createPost.validateResponse();

        // Read received post
        Post recievedPost = createPostFromJson(createPostResponse.jsonPath());

        // Modify received post for patch
        recievedPost.setTitle("A Brand New Title");
        recievedPost.setBody("Different body.");

        // modify post
        ModifyPost modifyPost = new ModifyPost(recievedPost.getId());
        modifyPost.addProperty("modified_post", recievedPost);

        Response modifyPostResponse = modifyPost.callAPIExpectSuccess();
        modifyPost.validateResponse();
    }


    private static Post createPostFromJson(JsonPath jsonPath) {
        User readUser = new User();
        readUser.setId(jsonPath.getInt("userId"));

        Post readPost = new Post();
        readPost.setId(jsonPath.getInt("id"));
        readPost.setTitle(jsonPath.getString("title"));
        readPost.setBody(jsonPath.getString("body"));
        readPost.setUser(readUser);

        return readPost;
    }
}
