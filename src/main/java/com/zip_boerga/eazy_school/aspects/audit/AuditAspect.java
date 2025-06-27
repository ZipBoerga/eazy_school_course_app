package com.zip_boerga.eazy_school.aspects.audit;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {
    @Before("within(@org.springframework.stereotype.Controller *)")
    public void setAuditContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            AuditContext.set(auth.getName());
        }
    }

    @Before("within(@org.springframework.stereotype.Controller *)")
    public void clearAuditContext() {
        AuditContext.clear();
    }
}
