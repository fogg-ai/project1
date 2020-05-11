package org.itstep.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(name = "role")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaRepositories
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @NotBlank
    @NotNull
    private String role;

    @OneToOne(mappedBy = "rolePerson", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    private UserGallery rolePerson;

}
