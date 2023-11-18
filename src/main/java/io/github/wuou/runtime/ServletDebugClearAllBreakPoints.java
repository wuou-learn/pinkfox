package io.github.wuou.runtime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/clearAllBreakPoint", loadOnStartup = 1)
public class ServletDebugClearAllBreakPoints extends ServletAbstractSuccess {

    @Override
    protected void doAction(HttpServletRequest request) {
        Chef.clearAllBreakPoints(false);
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
