package views;

import models.Task;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.filter.Filter;
import org.junit.Test;
import play.data.Form;
import play.libs.F;
import play.libs.WS;
import play.mvc.Content;
import play.mvc.Result;
import play.test.TestBrowser;
import tyrex.util.FastThreadLocal;

import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.Fluent.goTo;
import static play.test.Helpers.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.LinkedList;

public class IndexTest {
    @Test public void renderIndex(){
        Content html = views.html.index.render(tasksList(), new Form<Task>(Task.class));
        String htmlAsString = contentAsString(html);
        assertThat(htmlAsString).contains("<ui>first task");
        assertThat(htmlAsString).contains("<ui>second task");
    }

    @Test public void callIndex() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {
                Result result = callAction(
                        controllers.routes.ref.Application.tasks()
                );
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentType(result)).isEqualTo("text/html");
                assertThat(charset(result)).isEqualTo("utf-8");
                assertThat(contentAsString(result)).contains("KATA: TODOLIST");
            }
        });
    }

    @Test public void runInBrowser() {
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/tasks");
                browser.$("title").contains("KATA: TODOLIST");
                browser.fill("input",withName("label")).with("my test task 1");
                browser.$("#newTaskSubmit").click();
                assertThat(browser.url()).isEqualTo("http://localhost:3333/tasks");
                browser.$("title").contains("KATA: TODOLIST");
                assertThat(browser.find("h1").getTexts().get(0).toString()).isEqualTo("You have 1 tasks");
                assertThat(browser.find("ui").getTexts().get(0).toString()).contains("my test task 1");
            }
        });
    }

    private LinkedList<Task> tasksList() {
        LinkedList<Task> result = new LinkedList<Task>();
        result.add(new Task(1, "first task"));
        result.add(new Task(2, "second task"));
        return result;
    }
}
