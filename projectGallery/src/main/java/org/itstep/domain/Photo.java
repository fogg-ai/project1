package org.itstep.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "photo")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaRepositories
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
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

    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    private UserGallery photoPackage;
}
