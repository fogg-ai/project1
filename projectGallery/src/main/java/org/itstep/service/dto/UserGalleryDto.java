package org.itstep.service.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGalleryDto  {
    private Integer id;

    @NotBlank
    @NotNull
    @Length(max = 20)
    private String login;
    @NotBlank
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotNull
    @Length(max = 16)
    private String password;
    @NotBlank
    @NotNull
    private String role = "ROLE_USER";

    private PhotoDto photo;


}


