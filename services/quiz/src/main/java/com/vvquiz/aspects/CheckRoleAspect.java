package com.vvquiz.aspects;

import com.vvquiz.annotation.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Component
@Aspect
public class CheckRoleAspect {
    @Before("@annotation(checkRole)")
    public void checkRole(CheckRole checkRole) {
        List<String> allowedRoles = List.of(checkRole.value());
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String role = request.getHeader("role");
        System.out.println("Role: " + role);
        if (role == null || !allowedRoles.contains(role)) {
            throw new RuntimeException("You are not authorized to perform this operation");
        }
    }
}
