package com.example.project.services;

import com.example.project.models.Visit;
import com.example.project.repositories.VisitRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * данный код является частью логики фиксации посещения пользователями определенных страниц приложения
 * данные приходят с методов контроллеров помеченных аннотацией @Visitor
 */
@Service
@Transactional(readOnly = true)
public class VisitServise {
    private final VisitRepository visitRepository;
    private final HttpSession session;
    private final HttpServletRequest request;

    public VisitServise(VisitRepository visitRepository, HttpSession session, HttpServletRequest request) {
        this.visitRepository = visitRepository;
        this.session = session;
        this.request = request;
    }
    @Transactional
    public void save(String value){
        System.out.println("сохранение visitor");

        Visit visit = new Visit();
        visit.setSession(session.getId());
        if(request.getUserPrincipal()!=null)
            visit.setUsername(request.getUserPrincipal().getName());
        visit.setValue(value);
        visit.setTime(LocalDateTime.now());

        visitRepository.save(visit);

        if (request.getUserPrincipal()!=null&&request.getUserPrincipal().getName().equals("admin"))
            visitRepository.deleteAllBySession(session.getId());
    }
}
