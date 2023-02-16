package guru.qa.api.dummy.tests;

import guru.qa.api.dummy.models.post.GeneralPostList;
import guru.qa.api.dummy.models.post.Post;
import guru.qa.api.dummy.models.post.PostCreate;
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

import static guru.qa.api.dummy.data.Posts.generatePost;
import static guru.qa.api.dummy.data.Users.userGenerator;
import static guru.qa.api.dummy.steps.PostControllerSteps.*;
import static guru.qa.api.dummy.steps.UserControllerSteps.createUser;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Dummy")
@Story("Манипуляции с постами")
@Tags({@Tag("PostController"), @Tag("Api"), @Tag("Dummy")})
public class PostControllerTest {

    @Test
    @DisplayName("Получение списка постов")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void postList() {
        GeneralPostList postsList = getPostsList(3, 15);

        assertEquals(15, postsList.getLimit());
        assertEquals(3, postsList.getPage());
    }

    @Test
    @DisplayName("Создание нового поста")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void createPost() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        PostCreate postCreate = generatePost(createdUser.getId());
        Post newPost = createNewPost(postCreate);

        assertEquals(postCreate.getText(), newPost.getText());
        assertEquals(postCreate.getTags(), newPost.getTags());
        assertEquals(postCreate.getOwner(), newPost.getOwner().getId());
    }

    @Test
    @DisplayName("Изменение поста")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void updatePost() {
        UserFull user = userGenerator();
        Map<String, ?> fieldsForChange = Map.of(
                "text", "The War of the Worlds",
                "likes", 2
        );

        UserFull createdUser = createUser(user);
        PostCreate postCreate = generatePost(createdUser.getId());
        Post newPost = createNewPost(postCreate);

        Post updatedPost = updatePostById(newPost.getId(), fieldsForChange);

        assertEquals(fieldsForChange.get("text"), updatedPost.getText());
        assertEquals(fieldsForChange.get("likes"), updatedPost.getLikes());
    }

    @Test
    @DisplayName("Удаление поста")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void deletePost() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        PostCreate postCreate = generatePost(createdUser.getId());
        Post newPost = createNewPost(postCreate);

        deletePostById(newPost.getId());
        failedGetPostById(newPost.getId());
    }

    @Test
    @DisplayName("Проверка всех постов пользователя")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void checkAllUserPosts() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        PostCreate postCreate1 = generatePost(createdUser.getId());
        PostCreate postCreate2 = generatePost(createdUser.getId());
        Post newPost1 = createNewPost(postCreate1);
        Post newPost2 = createNewPost(postCreate2);

        GeneralPostList listOfPosts = getListOfPostsByUserId(createdUser.getId());

        assertEquals(2, listOfPosts.getTotal());
        assertTrue(
                listOfPosts.getData().stream()
                    .map(Post::getId)
                    .allMatch(id -> id.equals(newPost1.getId()) || id.equals(newPost2.getId()))
        );
    }

    @Test
    @DisplayName("Получение постов по тегу")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void postByTag() {
        UserFull user = userGenerator();

        UserFull createdUser = createUser(user);
        PostCreate postCreate = generatePost(createdUser.getId());
        Post newPost = createNewPost(postCreate);
        GeneralPostList listOfPosts = getListOfPostsByTag(newPost.getTags().get(0));

        assertTrue(
                listOfPosts.getData().stream()
                        .allMatch(post -> post.getTags().contains(newPost.getTags().get(0)))
        );
    }
}
