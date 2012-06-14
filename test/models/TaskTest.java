package models;

import org.junit.*;
import tests.BaseTest;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class TaskTest extends BaseTest {

    @Ignore
    @Test public void createTask(){
        final String TASK_LABEL = "my first task";
        Task createdTask = Task.create(new Task(TASK_LABEL));
        assertThat(createdTask.label).isEqualTo(TASK_LABEL);
        assertThat(createdTask.id).isNotNull();
    }

    @Ignore
    @Test public void deleteTask(){
        assertThat(Task.all().size()).isEqualTo(0);
        Task.create(new Task("1"));
        assertThat(Task.all().size()).isEqualTo(1);
        Task.delete(Task.all().get(0).id);
        assertThat(Task.all().size()).isEqualTo(0);
    }

    @Ignore
    @Test public void allTasks(){
        assertThat(Task.all().size()).isEqualTo(0);
        Task.create(new Task("my first task"));
        Task.create(new Task("my second task"));
        assertThat(Task.all().size()).isEqualTo(2);
        assertThat(Task.all().get(0).label).isEqualTo("my first task");
    }
}
