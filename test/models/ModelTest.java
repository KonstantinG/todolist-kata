package models;

import org.junit.Test;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

public class ModelTest {
    @Test public void CRUD(){
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            @Override
            public void run() {
                assertThat(Task.all().size()).isEqualTo(0);
                Task.create(new Task("my first task"));
                assertThat(Task.all().size()).isEqualTo(1);
                assertThat(Task.all().get(0).label).isEqualTo("my first task");
                Task.delete(Task.all().get(0).id);
                assertThat(Task.all().size()).isEqualTo(0);
            }
        });    
    }
}
