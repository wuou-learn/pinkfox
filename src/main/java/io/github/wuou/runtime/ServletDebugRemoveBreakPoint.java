package io.github.wuou.runtime;

import io.github.wuou.utils.Pair;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/removeBreakPoint", loadOnStartup = 1)
public class ServletDebugRemoveBreakPoint extends ServletAbstractSuccess {

    public ServletDebugRemoveBreakPoint() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {
        final String classFullQualifiedName = request.getParameter("classFullQualifiedName");
        final String pointIndex = request.getParameter("pointIndex");
        Chef.removeBreakPoint(Pair.of(classFullQualifiedName, Integer.parseInt(pointIndex)));
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
