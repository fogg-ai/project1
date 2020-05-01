package org.itstep.service.mapper;

import org.itstep.domain.UserGallery;
import org.itstep.service.dto.UserGalleryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface UserGalleryMapper extends EntityMapper<UserGalleryDto, UserGallery> {
    @Mapping(source = "id", target = "id")
    UserGalleryDto toDto(UserGallery userGallery);
    UserGallery toEntity(UserGalleryDto userGalleryDto);

}
