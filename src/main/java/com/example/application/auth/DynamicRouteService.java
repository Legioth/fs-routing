package com.example.application.auth;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This class is used to check if the client side route should be public
 * depending on the route configuration from the views.json file.
 */
@Component
public class DynamicRouteService {

    private final Map<String, HashSet<String>> routes = new HashMap<>();

    public boolean shouldBePublic(String path) {
        // Simpler implementation for prototype
        for (String route : routes.keySet()) {
            if (route.endsWith("/*")) {
                String routeWithoutWildcard = route.substring(0, route.length() - 2);
                if (path.startsWith(routeWithoutWildcard)) {
                    return true;
                }
            }
        }
        return routes.containsKey(path);
    }

    //If roles are needed for dynamic routes, this method could be updated
    public void addPublicRoute(String route) {
        if (route.isEmpty()) {
            routes.put("/", new HashSet<>());
        }
        routes.put(route, new HashSet<>());
    }
}
