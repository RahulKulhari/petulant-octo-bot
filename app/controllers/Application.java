package controllers;

import static play.data.Form.form;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.signupcmplt.*;

public class Application extends Controller {
	
	final static Form<User> usr=form(User.class); 
	
    public static Result index() {
        return ok(index.render(usr));
    }
    
    public static Result signup(){
    	
    	Form<User> filledForm=form(User.class).bindFromRequest();
    	
    	 if(!filledForm.hasErrors()) {
             if(filledForm.get().getUsername().equals("admin") || filledForm.get().getUsername().equals("guest")) {
                 filledForm.reject("username", "This username is already taken");
             }
         }
    	 
    	 if(!filledForm.field("password").valueOr("").isEmpty()) {
           if(!filledForm.field("password").valueOr("").equals(filledForm.field("repassword").value())) {
               filledForm.reject("repassword", "Password don't match");
           }
       }
         
         if(filledForm.hasErrors()) {
             return badRequest(index.render(filledForm));
         } else {
        	 
        	 
        	 
        	 
             User created = filledForm.get();
             created.save();
             return ok(signup.render(created));
         }
     }
    
    
}
