package com.http.wchat.controller.command.tool;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CreateFullURL {
    public static String create(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        Enumeration<String> paramNames2 = request.getParameterNames();
        String paramName;
        String paramValue;
        while (paramNames2.hasMoreElements()) {
            paramName = paramNames2.nextElement();

            paramValue = request.getParameter(paramName);
            url.append(paramName).append("=").append(paramValue).append("&");
        }

        url.insert(0, request.getRequestURL() + "?");

        return url.toString();
    }
}
