package guru.qa.api.dummy.data;

import com.github.javafaker.Faker;
import guru.qa.api.dummy.models.post.PostCreate;

import java.util.List;
import java.util.Locale;

public class Posts {

    private static final Faker faker = new Faker(new Locale("en"));

    public static PostCreate generatePost(String userId) {
        return PostCreate.builder()
                .text(faker.book().title())
                .image(faker.avatar().image())
                .likes(0)
                .tags(List.of("Book"))
                .owner(userId)
                .build();
    }
}
