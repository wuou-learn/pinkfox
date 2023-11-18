package io.github.wuou.runtime;

import io.github.wuou.clazz.BreakPoint;
import io.github.wuou.clazz.Clazz;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/addBreakPointExpression", loadOnStartup = 1)
public class ServletDebugAddBreakPointExpression extends ServletAbstractSuccess {

    public ServletDebugAddBreakPointExpression() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {
        final String classFullQualifiedName = request.getParameter("classFullQualifiedName");
        final String pointIndex = request.getParameter("pointIndex");
        final String expression = request.getParameter("expression");
        Clazz clazz = RuntimeContext.clazzMap.get(classFullQualifiedName);
        BreakPoint breakPoint = clazz.getBreakPoint(Integer.parseInt(pointIndex));
        breakPoint.setExpression(expression);
    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
