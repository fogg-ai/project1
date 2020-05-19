package org.itstep.filter;

import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FilterSecurityMinPhoto extends HandlerInterceptorAdapter {
    @Autowired
    UserGalleryRepository userGalleryRepository;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        int i = request.getRequestURI().lastIndexOf("/");
        String substring = request.getRequestURI().substring(10,i);
        if(substring.equals(name)) {
            return true;
        }else {
            response.sendError(403);
            return false;
        }
    }
}
