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
public class PhotoDto {
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @NotBlank
    @NotNull
    private String path;

    @NotBlank
    @NotNull
    private String pathUrl;


    private long size;

    private UserGalleryDto photoPackage;
}
