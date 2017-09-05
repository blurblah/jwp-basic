package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunchanlee on 2017. 9. 5..
 */
public interface Controller {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
