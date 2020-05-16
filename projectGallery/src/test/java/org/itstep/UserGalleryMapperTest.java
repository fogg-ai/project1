package org.itstep;


import org.itstep.domain.Photo;
import org.itstep.domain.Role;
import org.itstep.domain.UserGallery;
import org.itstep.service.dto.PhotoDto;
import org.itstep.service.dto.RoleDto;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.mapper.UserGalleryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(locations = "classpath:spring-jdbc.xml")
public class UserGalleryMapperTest {

    @Autowired
    UserGalleryMapper mapper;

    @Test
    void toEntity() {
        UserGalleryDto userGalleryDto = new UserGalleryDto(1, "test", "test@gmail.com","test",
                new RoleDto(1,"ROLE_USER",new UserGallery()),new PhotoDto(1,"path","path",123,1000000000,new UserGalleryDto()));
        UserGallery userGallery = mapper.toEntity(userGalleryDto);
        assertNotNull(userGallery);
        assertEquals("test", userGallery.getLogin());
        assertEquals("test@gmail.com", userGallery.getEmail());
        assertEquals("test", userGallery.getPassword());
        assertNotNull(userGallery.getRolePerson());
        assertEquals(1, userGallery.getRolePerson().getId());
        assertEquals("ROLE_USER", userGallery.getRolePerson().getRole());
        assertNotNull(userGallery.getPhoto());
        assertEquals(1, userGallery.getPhoto().getId());
        assertEquals("path", userGallery.getPhoto().getPath());
        assertEquals("path", userGallery.getPhoto().getPathUrl());
        assertEquals(123, userGallery.getPhoto().getSize());
        assertEquals(1000000000, userGallery.getPhoto().getMaxSize());
    }

    @Test
    void toDto() {

        UserGallery userGallery = new UserGallery(1, "test", "test@gmail.com","test",
                new Role(1,"ROLE_USER",null),new Photo(1,"path","path",123,2000000000,null));
        UserGalleryDto UserGalleryDto = mapper.toDto(userGallery);

        assertNotNull(UserGalleryDto);
        assertEquals(UserGalleryDto.getLogin(), "test");
        assertEquals(UserGalleryDto.getEmail(), "test@gmail.com");
        assertEquals(UserGalleryDto.getPassword(), "test");
        assertEquals(UserGalleryDto.getRolePerson().getRole(), "ROLE_USER");
        assertEquals(UserGalleryDto.getPhoto().getPath(), "path");
        assertEquals(UserGalleryDto.getPhoto().getPathUrl(), "path");
        assertEquals(UserGalleryDto.getPhoto().getMaxSize(), 2000000000);
        assertEquals(UserGalleryDto.getPhoto().getSize(), 123);
    }

}