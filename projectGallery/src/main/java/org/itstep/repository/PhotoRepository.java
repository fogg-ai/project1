package org.itstep.repository;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PhotoRepository extends JpaRepository<Photo,Integer> {
    @Transactional
    @Modifying
    @Query("update Photo p set p.size = ?1 where p.id = ?2")
    void setUserInfoById(long size, Integer id);

    Photo findUserByPathUrl(String pathUrl);
    Photo findUserByPath(String path);
}
