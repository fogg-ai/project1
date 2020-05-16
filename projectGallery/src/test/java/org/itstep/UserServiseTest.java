package org.itstep;



import org.itstep.domain.Photo;
import org.itstep.domain.Role;
import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.UserGalleryService;
import org.itstep.service.dto.UserGalleryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.itstep.repository.PhotoRepository;
import org.itstep.repository.RoleRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;



@ActiveProfiles("dev")
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class UserServiseTest {

    @Autowired
    UserGalleryRepository userGalleryRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserGalleryService userGalleryService;

    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindOneAndSave() {
        userGalleryRepository.save(new UserGallery(null, "test", "test@gmail.com","test",
                new Role(null,"ROLE_USER",new UserGallery()),new Photo(null,"path","path",123,2000000000,new UserGallery())));

        UserGalleryDto userGalleryDto = userGalleryService.findOne(1).get();


        assertNotNull(userGalleryDto);
        assertEquals(1, userGalleryDto.getId());
        assertEquals("test", userGalleryDto.getLogin());
        assertEquals("test", userGalleryDto.getPassword());
        assertEquals("test@gmail.com", userGalleryDto.getEmail());
        assertEquals("path", userGalleryDto.getPhoto().getPath());
        assertEquals("path", userGalleryDto.getPhoto().getPathUrl());
        assertEquals(2000000000, userGalleryDto.getPhoto().getMaxSize());
        assertEquals(123, userGalleryDto.getPhoto().getSize());
        assertEquals("ROLE_USER", userGalleryDto.getRolePerson().getRole());
    }
    @Test
    @Transactional
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testDelete() {
        userGalleryRepository.save(new UserGallery(null, "test", "test@gmail.com","test",
                new Role(null,"ROLE_USER",new UserGallery()),new Photo(null,"path","path",123,2000000000,new UserGallery())));
        userGalleryRepository.save(new UserGallery(null, "test1", "test1@gmail.com","test1",
                new Role(null,"ROLE_SUPERUSER",new UserGallery()),new Photo(null,"path1","path1",123,2000000000,new UserGallery())));
        userGalleryRepository.save(new UserGallery(null, "test2", "test2@gmail.com","test2",
                new Role(null,"ROLE_USER",new UserGallery()),new Photo(null,"path2","path2",123,2000000000,new UserGallery())));

        userGalleryRepository.findAll().size();
        assertEquals(9, userGalleryRepository.findAll().size());
        userGalleryService.delete(1);
        assertEquals(6, userGalleryRepository.findAll().size());
    }
}