package routes;

import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

public class RoutesTest {
    @Test public void routes() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {
                Result result = routeAndCall(fakeRequest("GET", "/x"));
                assertThat(result).isNull();
                result = routeAndCall(fakeRequest("GET", "/"));
                assertThat(result).isNotNull();
                result = routeAndCall(fakeRequest("GET", "/tasks"));
                assertThat(result).isNotNull();
                result = routeAndCall(fakeRequest("POST", "/tasks"));
                assertThat(result).isNotNull();
                result = routeAndCall(fakeRequest("GET", "/tasks/delete/1?"));
                assertThat(result).isNotNull();            }
        });

    }
}
