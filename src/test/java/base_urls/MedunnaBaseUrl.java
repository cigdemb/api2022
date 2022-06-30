package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Before;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MedunnaBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://medunna.com/api/").build();
    }
}
