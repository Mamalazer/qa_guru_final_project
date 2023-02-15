package guru.qa.api.dummy.specs;

import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static guru.qa.config.Prop.PROP;
import static guru.qa.utils.CustomApiListener.allureRequest;
import static org.hamcrest.Matchers.equalTo;

public class DummySpecs {

    public static RequestSpecification dummyRequest() {
        return allureRequest()
                .baseUri(PROP.getDummyBaseUrl())
                .contentType(ContentType.JSON)
                .header("app-id", PROP.getDummyToken());
    }

    public static ResponseSpecification notFoundResponse() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectBody("error", equalTo("RESOURCE_NOT_FOUND"))
                .build();
    }
}
