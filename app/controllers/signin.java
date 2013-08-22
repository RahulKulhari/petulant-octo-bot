package controllers;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.codehaus.jackson.node.ObjectNode;





//import controllers.SecurityController.Login;
import play.data.Form;
import play.data.validation.Constraints;
import play.data.validation.Validation;
import models.*;
import static play.data.Form.form;
import static play.mvc.Controller.response;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Result;
import views.html.index;
import views.html.sign.*;
import views.html.sign.logi;
import play.mvc.*;


public class signin extends Controller{
	
	
	public final static String username = "null";
	
	final static Form<Login> uf=form(Login.class);
	//static String s="welcome to login page";
	public static Result page()
	{
		
		
		return ok(logi.render(uf));
	}
	
	public static Result enter()
	{
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		
        if (loginForm.hasErrors()) {
            return badRequest(logi.render(loginForm));//errorsAsJson());
        }

        Login login = loginForm.get();

        User user = User.findByusernameAndPassword(login.username, login.password);

        if (user == null) {
        	
        	
        	return badRequest(logi.render(loginForm));//return ok(logi.render(loginForm));//unauthorized(user.getUsername());
        }
        else {
        	
        	//session("connected", user.getUsername());
        	
        	return ok(info.render(user));
        }
		
	}
	
	public static Result logou(){
		
		session().clear();
		//play.cache.Cache.remove("connected");
		
		return ok(logout.render());
	}
	
	

//	@Override
//	public Result call(Context arg0) throws Throwable {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public static class Login {

        @Constraints.Required
        public String username;

        @Constraints.Required
        public String password;
        
        public String validate() {

            User user = null;
            user = User.authenticate(username, password);
            if (user == null) {
                return Messages.get("invalid user or password");
            } 
            return null;
        }
        
    }



}
