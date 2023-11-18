package io.github.wuou.runtime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/cancelMuteAllBreakPoint", loadOnStartup = 1)
public class ServletDebugCancelMuteAllBreakPoints extends ServletAbstractSuccess {

    public ServletDebugCancelMuteAllBreakPoints() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {
        Chef.cancelMuteAllBreakPoint();
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
