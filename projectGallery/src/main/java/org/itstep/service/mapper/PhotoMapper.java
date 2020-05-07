package org.itstep.service.mapper;

import org.itstep.domain.Photo;
import org.itstep.service.dto.PhotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PhotoMapper extends EntityMapper<PhotoDto, Photo>{
    @Mapping(source = "id", target = "id")
    PhotoDto toDto(Photo photo);
    Photo toEntity(PhotoDto photoDto);
}


