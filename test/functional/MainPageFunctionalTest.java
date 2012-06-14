package functional;

import models.Task;
import org.fluentlenium.core.filter.Filter;
import org.junit.Ignore;
import org.junit.Test;
import play.libs.F;
import play.test.TestBrowser;

import static org.fest.assertions.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.with;
import static org.fluentlenium.core.filter.FilterConstructor.withName;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class MainPageFunctionalTest {
    @Test
    public void listOfTasks(){
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) throws Throwable {
                Task.create(new Task("my first task"));
                Task.create(new Task("my second task"));
                Task.create(new Task("my third task"));
                browser.goTo("http://localhost:3333/");                            
                assertThat(browser.$("title").getTexts().toString()).contains("KATA: TODOLIST");
                assertThat(browser.$("body").getTexts().toString()).contains("my first task");
                assertThat(browser.$("body").getTexts().toString()).contains("my second task");
                assertThat(browser.$("body").getTexts().toString()).contains("my third task");
            }
        });
    }
    @Test
    public void createNewTask(){
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) throws Throwable {
                browser.goTo("http://localhost:3333/");
                assertThat(browser.$("title").getTexts().toString()).contains("KATA: TODOLIST");
                browser.fill("input", withName("label")).with("my test task 1");
                browser.$("#newTaskSubmit").click();
                assertThat(browser.url()).isEqualTo("http://localhost:3333/tasks");
                assertThat(browser.find("h1").getTexts().get(0).toString()).contains("You have 1 tasks");
                assertThat(browser.find("ui").getTexts().get(0).toString()).contains("my test task 1");
            }
        });
    }

    @Test
    public void deleteTask(){
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) throws Throwable {
                Task.create(new Task("my first task"));
                Task.create(new Task("my second task"));
                browser.goTo("http://localhost:3333/");
                assertThat(browser.$("title").getTexts().toString()).contains("KATA: TODOLIST");
                browser.find("form:[action='/tasks/delete/1']").find("input [type='submit']").click();
                assertThat(browser.url()).isEqualTo("http://localhost:3333/tasks");
                assertThat(browser.find("ui").getTexts().get(0).toString().contains("my first task")).isEqualTo(false);
                assertThat(browser.find("ui").getTexts().get(0).toString().contains("my second task")).isEqualTo(true);
            }
        });
    }
}
