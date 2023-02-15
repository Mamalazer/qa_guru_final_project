package guru.qa.api.dummy.data;

import com.github.javafaker.Faker;
import guru.qa.api.dummy.models.user.Location;
import guru.qa.api.dummy.models.user.UserFull;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Users {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public static UserFull userGenerator() {
        return UserFull.builder()
                .title("mr")
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .gender("male")
                .email(faker.internet().emailAddress())
                .dateOfBirth(formatter.format(faker.date().birthday()))
                .phone(faker.phoneNumber().subscriberNumber(11))
                .picture(faker.avatar().image())
                .location(Location.builder()
                        .street(faker.address().streetName())
                        .city(faker.address().cityName())
                        .country(faker.country().name())
                        .timezone("+3:00")
                        .build())
                .build();
    }
}
