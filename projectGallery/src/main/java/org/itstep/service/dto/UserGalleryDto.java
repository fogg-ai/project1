package org.itstep.service.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.itstep.domain.UserGallery;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGalleryDto {
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

    private String photoPackage;


    @Override
    public String toString() {
        return "UserGalleryDto{" +
                "id=" + id +
                ", userName='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


