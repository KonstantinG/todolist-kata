package controllers;

import models.Task;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    public static Result tasks(){
        return ok(views.html.index.render(Task.all(), form(Task.class)));
    }
    
    public static Result newTask(){
        Form<Task> filledForm = form(Task.class).bindFromRequest();
        if(filledForm.hasErrors()){
            return badRequest(views.html.index.render(Task.all(), filledForm));
        }else{
            Task.create(filledForm.get());
            return redirect(routes.Application.tasks());
        }
    }
    
    public static Result deleteTask(Long id){
        Task.delete(id);
        return redirect(routes.Application.tasks());
    }
}