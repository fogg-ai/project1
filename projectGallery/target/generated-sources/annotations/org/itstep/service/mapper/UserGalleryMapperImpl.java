package org.itstep.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.itstep.domain.UserGallery;
import org.itstep.service.dto.UserGalleryDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-01T20:32:57+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 12.0.1 (Oracle Corporation)"
)
@Component
public class UserGalleryMapperImpl implements UserGalleryMapper {

    @Override
    public List<UserGallery> toEntity(List<UserGalleryDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserGallery> list = new ArrayList<UserGallery>( dtoList.size() );
        for ( UserGalleryDto userGalleryDto : dtoList ) {
            list.add( toEntity( userGalleryDto ) );
        }

        return list;
    }

    @Override
    public List<UserGalleryDto> toDto(List<UserGallery> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserGalleryDto> list = new ArrayList<UserGalleryDto>( entityList.size() );
        for ( UserGallery userGallery : entityList ) {
            list.add( toDto( userGallery ) );
        }

        return list;
    }

    @Override
    public UserGalleryDto toDto(UserGallery userGallery) {
        if ( userGallery == null ) {
            return null;
        }

        UserGalleryDto userGalleryDto = new UserGalleryDto();

        userGalleryDto.setId( userGallery.getId() );
        userGalleryDto.setLogin( userGallery.getLogin() );
        userGalleryDto.setEmail( userGallery.getEmail() );
        userGalleryDto.setPassword( userGallery.getPassword() );
        userGalleryDto.setRole( userGallery.getRole() );

        return userGalleryDto;
    }

    @Override
    public UserGallery toEntity(UserGalleryDto userGalleryDto) {
        if ( userGalleryDto == null ) {
            return null;
        }

        UserGallery userGallery = new UserGallery();

        userGallery.setId( userGalleryDto.getId() );
        userGallery.setLogin( userGalleryDto.getLogin() );
        userGallery.setEmail( userGalleryDto.getEmail() );
        userGallery.setPassword( userGalleryDto.getPassword() );
        userGallery.setRole( userGalleryDto.getRole() );

        return userGallery;
    }
}
