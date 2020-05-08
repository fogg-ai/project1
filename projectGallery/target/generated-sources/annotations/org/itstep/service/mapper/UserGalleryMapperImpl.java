package org.itstep.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.service.dto.PhotoDto;
import org.itstep.service.dto.UserGalleryDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-08T13:53:26+0300",
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
        userGalleryDto.setPhoto( photoToPhotoDto( userGallery.getPhoto() ) );

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
        userGallery.setPhoto( photoDtoToPhoto( userGalleryDto.getPhoto() ) );

        return userGallery;
    }

    protected PhotoDto photoToPhotoDto(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoDto photoDto = new PhotoDto();

        photoDto.setId( photo.getId() );
        photoDto.setPath( photo.getPath() );
        photoDto.setPathUrl( photo.getPathUrl() );
        photoDto.setSize( photo.getSize() );
        photoDto.setPhotoPackage( toDto( photo.getPhotoPackage() ) );

        return photoDto;
    }

    protected Photo photoDtoToPhoto(PhotoDto photoDto) {
        if ( photoDto == null ) {
            return null;
        }

        Photo photo = new Photo();

        photo.setId( photoDto.getId() );
        photo.setPath( photoDto.getPath() );
        photo.setPathUrl( photoDto.getPathUrl() );
        photo.setSize( photoDto.getSize() );
        photo.setPhotoPackage( toEntity( photoDto.getPhotoPackage() ) );

        return photo;
    }
}
