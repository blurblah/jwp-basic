package next.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunchanlee on 2017. 9. 5..
 */
public class RequestMapping {
    private Map<String, Controller> controllers;

    public RequestMapping() {
        this.controllers = new HashMap<>();
    }

    public void init() {
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/", new HomeController());
        controllers.put("/users", new ListUserController());
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users/profile", new ProfileController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/updateForm", new UpdateUserController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
