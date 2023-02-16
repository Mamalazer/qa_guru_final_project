package guru.qa.api.dummy.tests;

import guru.qa.api.dummy.models.comment.CommentPreview;
import guru.qa.api.dummy.models.comment.GeneralCommentList;
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

import static guru.qa.api.dummy.data.Posts.generatePost;
import static guru.qa.api.dummy.data.Users.userGenerator;
import static guru.qa.api.dummy.steps.CommentControllerSteps.*;
import static guru.qa.api.dummy.steps.PostControllerSteps.createNewPost;
import static guru.qa.api.dummy.steps.UserControllerSteps.createUser;
import static guru.qa.utils.RandomHelper.randomString;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Dummy")
@Story("Манипуляции с постами")
@Tags({@Tag("CommentController"), @Tag("Api"), @Tag("Dummy")})
public class CommentControllerTest {

    @Test
    @DisplayName("Получение списка коментов")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void commentList() {
        GeneralCommentList commentsList = getCommentsList(4, 50);

        assertEquals(50, commentsList.getLimit());
        assertEquals(4, commentsList.getPage());
    }

    @Test
    @DisplayName("Добавление комента к посту")
    @Owner("d.kuznetsov")
    @Severity(BLOCKER)
    public void addComment() {
        UserFull user = userGenerator();
        String text = randomString(20);

        UserFull createdUser = createUser(user);
        PostCreate postCreate = generatePost(createdUser.getId());
        Post newPost = createNewPost(postCreate);
        CommentPreview comment = addNewComment(text, newPost.getId(), createdUser.getId());

        assertEquals(text, comment.getMessage());
        assertEquals(createdUser.getId(), comment.getOwner().getId());
    }

    @Test
    @DisplayName("Получение списка коментов пользователя")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void getUserComments() {
        UserFull user = userGenerator();
        String text1 = randomString(31);
        String text2 = randomString(45);

        UserFull createdUser = createUser(user);
        PostCreate postCreate1 = generatePost(createdUser.getId());
        PostCreate postCreate2 = generatePost(createdUser.getId());
        Post newPost1 = createNewPost(postCreate1);
        Post newPost2 = createNewPost(postCreate2);

        addNewComment(text1, newPost1.getId(), createdUser.getId());
        addNewComment(text2, newPost2.getId(), createdUser.getId());
        GeneralCommentList commentsList = getCommentsListByUser(createdUser.getId(), 1, 50);

        assertEquals(2, commentsList.getTotal());
    }

    @Test
    @DisplayName("Удаление комента")
    @Owner("d.kuznetsov")
    @Severity(NORMAL)
    public void deleteComment() {
        UserFull user = userGenerator();
        String text = randomString(20);

        UserFull createdUser = createUser(user);
        PostCreate postCreate = generatePost(createdUser.getId());
        Post newPost = createNewPost(postCreate);
        CommentPreview comment = addNewComment(text, newPost.getId(), createdUser.getId());
        deleteCommentById(comment.getId());
        GeneralCommentList commentsList = getCommentsListByPostId(newPost.getId(), 1, 20);

        assertEquals(0, commentsList.getTotal());
    }
}
