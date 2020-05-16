package org.itstep.repository;


import org.itstep.domain.UserGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface UserGalleryRepository extends JpaRepository<UserGallery,Integer> {
    UserGallery findUserByLogin(String login);

}
