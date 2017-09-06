package next.controller;

import core.mvc.Controller;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserDao dao = new UserDao();
        req.setAttribute("users", dao.findAll());
        return "home.jsp";
    }
}
