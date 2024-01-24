package com.example.application.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * This class is used to check if the route should be public or not
 * depending on the route configuration from the views.json file.
 */
public class PublicClientViewRequestMatcher implements org.springframework.security.web.util.matcher.RequestMatcher {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Override
    public boolean matches(HttpServletRequest request) {
        return dynamicRouteService.shouldBePublic(request.getRequestURI());
    }
}
