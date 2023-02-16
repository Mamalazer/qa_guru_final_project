package guru.qa.api.dummy.steps;

import guru.qa.api.dummy.models.post.GeneralPostList;
import guru.qa.api.dummy.models.post.Post;
import guru.qa.api.dummy.models.post.PostCreate;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static guru.qa.api.dummy.specs.DummySpecs.dummyRequest;
import static guru.qa.api.dummy.specs.DummySpecs.notFoundResponse;
import static io.restassured.RestAssured.given;

public class PostControllerSteps {

    @Step("Получить список постов")
    public static GeneralPostList getPostsList(int page, int limit) {
        return given()
                .spec(dummyRequest())
                .basePath("/post")
                .param("page", page)
                .param("limit", limit)
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralPostList.class);
    }

    @Step("Создать новый пост")
    public static Post createNewPost(PostCreate postCreate) {
        return given()
                .spec(dummyRequest())
                .basePath("/post/create")
                .body(postCreate).log().all()
                .post()
                .then().log().all()
                .statusCode(200)
                .extract().as(Post.class);
    }

    /**
     * owner field is forbidden to update
     */
    @Step("Изменение поста с id = {postId}")
    public static Post updatePostById(String postId, Map<String, ?> chanhedFields) {
        return given()
                .spec(dummyRequest())
                .basePath("/post/" + postId)
                .body(chanhedFields).log().all()
                .put()
                .then().log().all()
                .statusCode(200)
                .extract().as(Post.class);
    }

    @Step("Удалить пост с id = {postId}")
    public static String deletePostById(String postId) {
        return given()
                .spec(dummyRequest())
                .basePath("/post/" + postId)
                .delete()
                .then().log().all()
                .statusCode(200)
                .extract().asString();
    }

    private static Response getPostById(String postId) {
        return given()
                .spec(dummyRequest())
                .basePath("/post/" + postId)
                .get();
    }

    @Step("Успешное получение поста с id = {postId}")
    public static Post successGetPostById(String postId) {
        return getPostById(postId)
                .then().log().all()
                .statusCode(200)
                .extract().as(Post.class);
    }

    @Step("Получение несуществующего поста с id = {postId}")
    public static void failedGetPostById(String postId) {
        getPostById(postId)
                .then().log().all()
                .spec(notFoundResponse());
    }

    @Step("Получение списка постов пользователя с id = {userId}")
    public static GeneralPostList getListOfPostsByUserId(String userId) {
        return given()
                .spec(dummyRequest())
                .basePath("/user/" + userId + "/post")
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralPostList.class);
    }

    @Step("Получение списка постов по тегу = {tag}")
    public static GeneralPostList getListOfPostsByTag(String tag) {
        return given()
                .spec(dummyRequest())
                .basePath("/tag/" + tag + "/post")
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralPostList.class);
    }
}
