package api.util.assertions;


import api.util.conditions.Condition;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@RequiredArgsConstructor
public class AssertableResponse {

    private static final Logger log = LogManager.getLogger(AssertableResponse.class);
    private final Response response;

    public AssertableResponse shouldHave(Condition condition) {
        log.log(Level.INFO, "About to check condition {0}", condition);
        condition.check(response);
        return this;
    }

    public <T> T asClass(Class<T> tClass) {
        return response.as(tClass);
    }
}
