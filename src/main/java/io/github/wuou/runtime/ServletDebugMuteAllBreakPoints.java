package io.github.wuou.runtime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/muteAllBreakPoint", loadOnStartup = 1)
public class ServletDebugMuteAllBreakPoints extends ServletAbstractSuccess {

    public ServletDebugMuteAllBreakPoints() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {
        Chef.muteAllBreakPoint();
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
