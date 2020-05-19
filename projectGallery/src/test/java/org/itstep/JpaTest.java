package org.itstep;


import static org.junit.jupiter.api.Assertions.*;


import org.itstep.domain.Photo;
import org.itstep.domain.Role;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.RoleRepository;
import org.itstep.repository.UserGalleryRepository;

import org.itstep.service.dto.PhotoDto;
import org.itstep.service.dto.UserGalleryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;



@ActiveProfiles("dev")
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class JpaTest {

    @Autowired
    UserGalleryRepository userGalleryRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getUserGallery() {
        assertNotNull(userGalleryRepository);
        userGalleryRepository.save(new UserGallery(null, "test", "test@gmail.com","test",
                new Role(null,"ROLE_USER",new UserGallery()),new Photo(null,"path","path","minPhotoPath",
                "minPhotoUrl",123,2000000000,new UserGallery())));


        UserGallery one = userGalleryRepository.getOne(1);
        assertEquals("test", one.getLogin());
        assertNotNull(one);
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getPhoto() {
        photoRepository.save(new Photo(null,"path","path","minPhotoPath",
                "minPhotoUrl",123,2000000000,new UserGallery()));

        Photo one = photoRepository.getOne(1);
        assertEquals("path", one.getPath());
        assertEquals("path", one.getPathUrl());
        assertEquals(123, one.getSize());
        assertEquals(2000000000, one.getMaxSize());
        assertNotNull(one);
    }

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void getRole() {
        roleRepository.save(new Role(null,"ROLE_USER",new UserGallery()));
        roleRepository.save(new Role(null,"ROLE_SUPERUSER",new UserGallery()));

        Role one = roleRepository.getOne(1);
        assertEquals("ROLE_USER", one.getRole());
        Role two = roleRepository.getOne(3);
        assertEquals("ROLE_SUPERUSER", two.getRole());
    }
}