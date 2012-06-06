package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Task extends Model {
    @Id
    public Long id;
    
    public String label;
    
    public static Finder<Long, Task> find = new Finder<Long, Task>(Long.class, Task.class);

    public Task(){}

    public Task(String label) {
        this.label = label;
    }

    public Task(long id, String label) {
        this(label);
        this.id = id;
    }

    public static List<Task> all(){
        return find.all();
    }
    
    public static void create(Task task){
        task.save();
    }
    
    public static void delete(Long id){
       find.ref(id).delete();
    }
}
