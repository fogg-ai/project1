package org.itstep.controller;

import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.uploadPhoto.UploadPhotoServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadPhoto {
    final
    UploadPhotoServise uploadPhotoServise;

    final
    UserGalleryRepository userGalleryRepository;


    public UploadPhoto(UploadPhotoServise uploadPhotoServise, UserGalleryRepository userGalleryRepository) {
        this.uploadPhotoServise = uploadPhotoServise;
        this.userGalleryRepository = userGalleryRepository;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("photo") MultipartFile f) throws IOException {
        UserGalleryDto user = (UserGalleryDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        String photoPackage = userByLogin.getPhotoPackage();
        uploadPhotoServise.uploadFile(f,photoPackage);
        System.out.println(f.getOriginalFilename());
        return "/gallery";
    }

    @GetMapping("/upload")
    public String uploadGet(){
        return "/gallery";
    }

}
