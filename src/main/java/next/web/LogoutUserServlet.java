package next.web;

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
 * Created by sunchanlee on 2017. 9. 5..
 */
@WebServlet("/user/logout")
public class LogoutUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LogoutUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if(value != null) {
            session.removeAttribute("user");
            log.info("User {} logged out.", ((User)value).getUserId());
        }

        resp.sendRedirect("/index.jsp");
    }
}
