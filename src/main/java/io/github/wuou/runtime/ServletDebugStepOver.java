package io.github.wuou.runtime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/stepOver", loadOnStartup = 1)
public class ServletDebugStepOver extends ServletAbstractSuccess {

    public ServletDebugStepOver() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {
        Chef.stepOver();
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
