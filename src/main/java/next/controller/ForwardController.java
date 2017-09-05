package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunchanlee on 2017. 9. 5..
 */
public class ForwardController implements Controller {

    private String url;

    public ForwardController(String url) {
        this.url = url;
        if(url == null) {
            throw new NullPointerException("Forward URL is null.");
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return this.url;
    }
}
