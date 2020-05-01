package org.itstep.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService<D> {
    /**
     * Save a entity.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    D save(D dto);

    /**
     * Get all the entities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<D> findAll(Pageable pageable);

    /**
     * Get the "id" groups.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<D> findOne(Integer id);

    /**
     * Delete the "id" groups.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}

