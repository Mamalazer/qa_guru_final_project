package guru.qa.api.dummy.steps;

import guru.qa.api.dummy.models.user.GeneralUserList;
import guru.qa.api.dummy.models.user.UserFull;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static guru.qa.api.dummy.specs.DummySpecs.dummyRequest;
import static guru.qa.api.dummy.specs.DummySpecs.notFoundResponse;
import static io.restassured.RestAssured.given;

public class UserControllerSteps {

    @Step("Получить список пользователей на странице {page} с количеством отображаемых пользователей = {limit}")
    public static GeneralUserList getUsersList(int page, int limit) {
        return given()
                .spec(dummyRequest())
                .basePath("/user")
                .param("page", page)
                .param("limit", limit)
                .get()
                .then().log().all()
                .statusCode(200)
                .extract().as(GeneralUserList.class);
    }

    @Step("Создать нового пользователя")
    public static UserFull createUser(UserFull user) {
        return given()
                .spec(dummyRequest())
                .basePath("/user/create")
                .body(user).log().all()
                .post()
                .then().log().all()
                .statusCode(200)
                .extract().as(UserFull.class);
    }

    private static Response getUserById(String userId) {
        return given()
                .spec(dummyRequest())
                .basePath("/user/" + userId)
                .get();
    }

    @Step("Получить существующего пользователя с id = {userId}")
    public static UserFull successGetUserById(String userId) {
        return getUserById(userId)
                .then().log().all()
                .statusCode(200)
                .extract().as(UserFull.class);
    }

    @Step("Получить несуществующего пользователя с id = {userId}")
    public static void failedGetUserById(String userId) {
        getUserById(userId)
                .then().log().all()
                .spec(notFoundResponse());
    }

    /**
     * email is forbidden to update
     */
    @Step("Изменить пользователя с id = {userId}")
    public static UserFull updateUserById(String userId, Map<String, String> fieldsForChange) {
        return given()
                .spec(dummyRequest())
                .basePath("/user/" + userId)
                .body(fieldsForChange).log().all()
                .put()
                .then().log().all()
                .statusCode(200)
                .extract().as(UserFull.class);
    }

    @Step("Удалить пользователя с id = {userId}")
    public static String deleteUserById(String userId) {
        return given()
                .spec(dummyRequest())
                .basePath("/user/" + userId)
                .delete()
                .then().log().all()
                .statusCode(200)
                .extract().asString();
    }
}
