package org.itstep.filter;

import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class FilterSecuritu extends HandlerInterceptorAdapter {
    @Autowired
    UserGalleryRepository userGalleryRepository;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        int i = request.getRequestURI().lastIndexOf("/");
        String substring = request.getRequestURI().substring(7,i);
        if(substring.equals(name)) {
            return true;
        }else {
            response.sendError(403);
            return false;
        }
    }


}
