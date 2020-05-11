package org.itstep.service.mapper;

import org.itstep.domain.Role;
import org.itstep.domain.UserGallery;
import org.itstep.service.dto.RoleDto;
import org.itstep.service.dto.UserGalleryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role>  {
    @Mapping(source = "id", target = "id")
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
