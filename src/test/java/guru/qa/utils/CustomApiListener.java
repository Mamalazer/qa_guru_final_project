package guru.qa.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CustomApiListener {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }

    public static RequestSpecification jsonRequest() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(withCustomTemplates())
                .build();
    }
}
