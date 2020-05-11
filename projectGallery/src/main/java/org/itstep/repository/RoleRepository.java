package org.itstep.repository;


import org.itstep.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Transactional
    @Modifying
    @Query("update Role r set r.role = ?1 where r.id = ?2")
    void setNewRole(String role, Integer id);
}
