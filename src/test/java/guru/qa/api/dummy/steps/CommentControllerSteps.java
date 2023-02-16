package guru.qa.api.dummy.steps;

import guru.qa.api.dummy.models.comment.CommentCreate;
import guru.qa.api.dummy.models.comment.CommentPreview;
import guru.qa.api.dummy.models.comment.GeneralCommentList;
import io.qameta.allure.Step;

import static guru.qa.api.dummy.specs.DummySpecs.dummyRequest;
import static io.restassured.RestAssured.given;

public class CommentControllerSteps {

    @Step("Получить список коментов")
    public static GeneralCommentList getCommentsList(int page, int limit) {
        return given()
                .spec(dummyRequest())
                .basePath("/comment")
                .param("page", page)
                .param("limit", limit)
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralCommentList.class);
    }

    @Step("Добавить новый комент '{message}' к посту {postId}")
    public static CommentPreview addNewComment(String message, String postId, String userId) {
        CommentCreate comment = CommentCreate.builder()
                .message(message)
                .post(postId)
                .owner(userId)
                .build();

        return given()
                .spec(dummyRequest())
                .basePath("/comment/create")
                .body(comment).log().all()
                .post()
                .then().log().all()
                .statusCode(200)
                .extract().as(CommentPreview.class);
    }

    @Step("Получить список коментов пользователя с id = {userId}")
    public static GeneralCommentList getCommentsListByUser(String userId, int page, int limit) {
        return given()
                .spec(dummyRequest())
                .basePath("/user/" + userId + "/comment")
                .param("page", page)
                .param("limit", limit)
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralCommentList.class);
    }

    @Step("Удаление комента с id = {commentId}")
    public static String deleteCommentById(String commentId) {
        return given()
                .spec(dummyRequest())
                .basePath("/comment/" + commentId)
                .delete()
                .then().log().all()
                .statusCode(200)
                .extract().asString();
    }

    @Step("Получить коменты к посту с id = {postId}")
    public static GeneralCommentList getCommentsListByPostId(String postId, int page, int limit) {
        return given()
                .spec(dummyRequest())
                .basePath("/post/" + postId + "/comment")
                .param("page", page)
                .param("limit", limit)
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralCommentList.class);
    }
}
