package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by sunchanlee on 2017. 9. 4..
 */
@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if(user == null || !req.getParameter("password").equals(user.getPassword())) {
            log.info("User {}'s password is invalid.", userId);
            resp.sendRedirect("/user/login_failed.html");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        log.info("User {} logged in.", userId);
        resp.sendRedirect("/index.jsp");
    }
}
