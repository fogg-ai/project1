package org.itstep;

import org.itstep.domain.Photo;
import org.itstep.domain.Role;
import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@Transactional
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class RepositoryTest {

    @Autowired
    UserGalleryRepository userGalleryRepository;

    @Test
    void getFindUserByLoginTest() {
        assertNotNull(userGalleryRepository);
        userGalleryRepository.save(new UserGallery(null, "123", "test@gmail.com","test",
                new Role(null,"ROLE_USER",new UserGallery()),new Photo(null,"path","path",123,
                2000000000,new UserGallery())));

        UserGallery all = userGalleryRepository.findUserByLogin("123");
        assertNotNull(all);
        assertEquals("123", all.getLogin());
        assertEquals("test@gmail.com", all.getEmail());
        assertEquals("ROLE_USER", all.getRolePerson().getRole());
        assertEquals("path", all.getPhoto().getPath());
        assertEquals(2000000000, all.getPhoto().getMaxSize());
    }

}