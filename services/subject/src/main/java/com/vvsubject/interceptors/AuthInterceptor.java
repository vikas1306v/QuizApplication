package com.vvsubject.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final Map<String, Pair<List<String>,List<String>>> allowedRoles = new HashMap<>();
    static {
        allowedRoles.put("/subject",new Pair<>(List.of("GET","PUT","POST","DELETE"),List.of("ADMIN","STUDENT")));
        allowedRoles.put("/subject/get",new Pair<>( List.of("GET"),List.of("ADMIN","STUDENT")));
    }
    public boolean preHandle(@Nullable HttpServletRequest request,@Nullable HttpServletResponse response,@Nullable Object handler) throws Exception {
     if(response==null)
         return false;
     if(request==null)
         return false;
     if(request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ")) {
         response.setStatus(HttpStatus.UNAUTHORIZED.value());
         return false;
     }
     String role = request.getHeader("role");
        System.out.println(role);
     String requestedEndpoint = extractEndpoint(request.getRequestURI());
     String method=request.getMethod();
     System.out.println(method);
     System.out.println(requestedEndpoint);
     if (!isRoleAllowed(requestedEndpoint,role,method)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
     }
     return true;
    }
    private String extractEndpoint(String requestURI) {
        requestURI = requestURI.replaceAll("/+$", "");
        return requestURI.replaceAll("/\\w+$", "");
    }
    private boolean isRoleAllowed(String requestedEndpoint, String role,String method) {
        Pair<List<String>, List<String>> pair = allowedRoles.get(requestedEndpoint);
        System.out.println(pair);
        return pair.a.contains(method) && pair.b.contains(role);
    }
}
