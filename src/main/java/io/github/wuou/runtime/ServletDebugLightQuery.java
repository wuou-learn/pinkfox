package io.github.wuou.runtime;

import io.github.wuou.clazz.LightQuery;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pinkfox/lightQuery", loadOnStartup = 1)
public class ServletDebugLightQuery extends ServletAbstractSuccess {

    public ServletDebugLightQuery() {
        super();
    }

    @Override
    protected void doAction(HttpServletRequest request) {

    }

    @Override
    protected Object successData(HttpServletRequest request, HttpServletResponse response) {
        LightQuery lightQuery = new LightQuery();
        lightQuery.setChanging(RuntimeContext.isChanging);
        return lightQuery;
    }
}
