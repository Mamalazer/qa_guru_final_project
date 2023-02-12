package guru.qa.web.swaglabs.data;

import lombok.Getter;

@Getter
public class User {

    private String login;
    private String password = "secret_sauce";
    private String firstName;
    private String lastName;
    private String zipCode;

    public User(String login, String firstName, String lastName, String zipCode) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
    }

    public User(String login) {
        this.login = login;
    }
}
