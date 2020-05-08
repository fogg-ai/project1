package org.itstep.service.security;


import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomDetailsService implements UserDetailsService {

    final
    UserGalleryRepository userGalleryRepository;

    public CustomDetailsService(UserGalleryRepository userGalleryRepository) {
        this.userGalleryRepository = userGalleryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {


        return userGalleryRepository.findUserByLogin(login);

    }
}
