package conf;

import controllers.Application;
import org.junit.Ignore;
import org.junit.Test;
import play.mvc.Result;
import tests.BaseTest;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * GET /tasks => /tasks with list of tasks
 * POST /tasks => create new task and redirect to GET /tasks
 * GET /tasks/delete/:id => delete task with id = :id and redirect to GET /tasks
 * GET / => redirect to /tasks
 */
public class RouteTest extends BaseTest{
    @Test public void badRequestRoute(){
        Result result = routeAndCall(fakeRequest("GET","/badReq/ddd"));
        assertThat(result).isNull();
    }

    @Test public void indexPageRedirectToTasksPageRoute() {
        Result result = routeAndCall(fakeRequest("GET","/"));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(303);
    }

    @Test public void tasksPageRoute() {
        Result result = routeAndCall(fakeRequest("GET","/tasks"));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(200);
    }

    @Test public void taskNewTask(){
        Result result = routeAndCall(fakeRequest("POST", "/tasks"));
        assertThat(status(result)).isEqualTo(303);
    }

    @Test public void deleteTask(){
        Result result = routeAndCall(fakeRequest("GET","/tasks/delete/1?"));
        assertThat(status(result)).isEqualTo(303);
    }
    
    @Test public void javascriptRoutes(){
        Result result = routeAndCall(fakeRequest("GET", "/assets/javascripts/routes"));
        assertThat(status(result)).isEqualTo(200);
    }

}
