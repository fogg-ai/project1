package org.itstep.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.domain.UserGallery;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto  {
    Integer id;

    @NotBlank
    @NotNull
    private String path;

    @NotBlank
    @NotNull
    private String pathUrl;


    @NotBlank
    @NotNull
    private String minPhotoPath;

    @NotBlank
    @NotNull
    private String minPhotoUrl;

    private long size;

    private long maxSize;

    private UserGalleryDto photoPackage;
}
