package guru.qa.api.dummy.tests;

import guru.qa.api.dummy.models.user.GeneralUserList;
import guru.qa.api.dummy.models.user.UserFull;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static guru.qa.api.dummy.data.Users.userGenerator;
import static guru.qa.api.dummy.steps.UserControllerSteps.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Dummy")
@Story("Манипуляции с пользователем")
@Tags({@Tag("UserController"), @Tag("Api"), @Tag("Dummy")})
public class UserControllerTest {

    @Test
    @DisplayName("Получение списка пользователей")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void userList() {
        GeneralUserList usersList = getUsersList(2, 30);

        assertEquals(30, usersList.getLimit());
        assertEquals(2, usersList.getPage());
    }

    @Test
    @DisplayName("Создание нового пользователя")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void createNewUser() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        successGetUserById(createdUser.getId());

        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    @DisplayName("Изменение пользователя")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void changeUserInfo() {
        UserFull user = userGenerator();
        Map<String, String> fieldsForChange = Map.of(
                "firstName", "Dan",
                "lastName", "Smith"
        );

        UserFull createdUser = createUser(user);
        UserFull changedUser = updateUserById(createdUser.getId(), fieldsForChange);

        assertEquals(fieldsForChange.get("firstName"), changedUser.getFirstName());
        assertEquals(fieldsForChange.get("lastName"), changedUser.getLastName());
    }

    @Test
    @DisplayName("Удаление пользователя")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void deleteUser() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        deleteUserById(createdUser.getId());
        failedGetUserById(createdUser.getId());
    }
}
