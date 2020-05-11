package org.itstep.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.domain.UserGallery;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer id;
    @NotBlank
    @NotNull
    private String role;


    private UserGallery rolePerson;
}
