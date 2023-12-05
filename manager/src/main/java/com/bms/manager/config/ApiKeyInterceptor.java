/*
package com.bms.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ApiKeyInterceptor implements HandlerInterceptor {
    @Autowired
    WebMvcConfig webMvcConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("X-API-Key");

        if (apiKey == null || !apiKey.equals(webMvcConfig.getAPI_KEY())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return false;
        }

        return true;
    }
}
*/
