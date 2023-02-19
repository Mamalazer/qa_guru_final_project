package guru.qa.utils.browserstack;

import guru.qa.utils.browserstack.models.BrowserStackSessionInfo;

import static guru.qa.config.Prop.PROP;
import static io.restassured.RestAssured.given;

public class BrowserStackHelper {

    public static BrowserStackSessionInfo getSessionInfo(String sessionId) {
        return given()
                .baseUri("https://api.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .auth().basic(PROP.getBrowserStackUser(), PROP.getBrowserStackPassword())
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().as(BrowserStackSessionInfo.class);
    }

    public static String getBrowserStackLog(String url) {
        return given()
                .baseUri(url)
                .auth().basic(PROP.getBrowserStackUser(), PROP.getBrowserStackPassword())
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().asString();
    }
}
