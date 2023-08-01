package com.example.project.aop;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * данный код является частью логики фиксации посещения пользователями определенных страниц приложения
 * данные приходят с методов контроллеров помеченных аннотацией @Visitor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Visitor {

    @AliasFor("message")
    String value() default "";

    @AliasFor("value")
    String message() default "";

}