package next.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunchanlee on 2017. 9. 5..
 */
@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private RequestMapping requestMapping;

    @Override
    public void init() throws ServletException {
        this.requestMapping = new RequestMapping();
        this.requestMapping.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = this.requestMapping.getController(req.getRequestURI());
        String uri = controller.execute(req, resp);
        if (uri.indexOf("redirect:") > -1) {
            // redirect
            resp.sendRedirect(uri.substring("redirect:".length()));
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }
}