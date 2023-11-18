package io.github.wuou.runtime;

import io.github.wuou.clazz.BreakPoint;
import io.github.wuou.clazz.BreakPointExpression;
import io.github.wuou.clazz.Clazz;
import io.github.wuou.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/getBreakPointExpression", loadOnStartup = 1)
public class ServletDebugGetBreakPointExpression extends ServletAbstractSuccess {

    public ServletDebugGetBreakPointExpression() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {

    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        final String classFullQualifiedName = request.getParameter("classFullQualifiedName");
        final String pointIndex = request.getParameter("pointIndex");
        if(StringUtils.isEmpty(classFullQualifiedName) || StringUtils.isEmpty(pointIndex)){
            return null;
        }
        Clazz clazz = RuntimeContext.clazzMap.get(classFullQualifiedName);

        BreakPointExpression breakPointExpression = new BreakPointExpression();
        BreakPoint breakPoint = clazz.getBreakPoint(Integer.parseInt(pointIndex));
        breakPointExpression.setExpression(breakPoint.getExpression());
        return breakPointExpression;

    }
}
