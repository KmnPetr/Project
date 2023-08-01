package com.example.project.aop;

import com.example.project.services.VisitServise;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * данный код является частью логики фиксации посещения пользователями определенных страниц приложения
 * данные приходят с методов контроллеров помеченных аннотацией @Visitor
 */
@Aspect
@Component
public class VisitorAspect {
    private final VisitServise visitServise;

    public VisitorAspect(VisitServise visitServise, HttpServletRequest request, HttpSession session) {
        this.visitServise = visitServise;
    }

    @After("@annotation(visitor)")
    public void VisitEnamlogVisitor(JoinPoint joinPoint, Visitor visitor) {
        
        visitServise.save(visitor.value());
    }
}
