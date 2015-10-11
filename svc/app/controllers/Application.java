package controllers;

import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        String serverName = request().host();

        return ok(index.render("Your new application is ready."));
    }
}
