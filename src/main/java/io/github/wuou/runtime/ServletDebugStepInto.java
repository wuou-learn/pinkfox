package io.github.wuou.runtime;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/stepInto", loadOnStartup = 1)
public class ServletDebugStepInto extends ServletAbstractSuccess {

    public ServletDebugStepInto() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {
        Chef.stepInto();
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
