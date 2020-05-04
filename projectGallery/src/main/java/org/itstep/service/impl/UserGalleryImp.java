package org.itstep.service.impl;


import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.mapper.UserGalleryMapper;
import org.itstep.service.UserGalleryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@EnableJpaRepositories
public class UserGalleryImp implements UserGalleryService {
    final UserGalleryRepository userGalleryRepository;
    final UserGalleryMapper userGalleryMapper;


    public UserGalleryImp(UserGalleryRepository userGalleryRepository,UserGalleryMapper userGalleryMapper) {
        this.userGalleryRepository = userGalleryRepository;
        this.userGalleryMapper = userGalleryMapper;
    }

    @Override
    public UserGalleryDto save(UserGalleryDto dto) {
        UserGallery userGallery = userGalleryMapper.toEntity(dto);
        userGalleryRepository.save(userGallery);
        return userGalleryMapper.toDto(userGallery);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<UserGalleryDto> findAll(Pageable pageable) {
        return userGalleryRepository.findAll(pageable).map(userGalleryMapper::toDto);
    }

    @Override
    public Optional<UserGalleryDto> findOne(Integer id) {
        return userGalleryRepository.findById(id).map(userGalleryMapper::toDto);
    }

    @Override
    public void delete(Integer id) {
        userGalleryRepository.deleteById(id);
    }
}
