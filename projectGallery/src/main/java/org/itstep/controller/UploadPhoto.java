package org.itstep.controller;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.uploadPhoto.DeleteService;
import org.itstep.service.uploadPhoto.UploadPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class UploadPhoto {
    final
    DeleteService deleteService;
    final
    UploadPhotoService uploadPhotoService;

    final
    UserGalleryRepository userGalleryRepository;

    final
    PhotoRepository photoRepository;


    public UploadPhoto(UploadPhotoService uploadPhotoService, UserGalleryRepository userGalleryRepository, PhotoRepository photoRepository, DeleteService deleteService) {
        this.uploadPhotoService = uploadPhotoService;
        this.userGalleryRepository = userGalleryRepository;
        this.photoRepository = photoRepository;
        this.deleteService = deleteService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("photo") MultipartFile f, Model model) {
       uploadPhotoService.operationFile(f);
        return "redirect:/gallery";
    }


    @GetMapping("/upload")
    public String uploadGet(Model model) {

        return "/gallery";
    }

    @GetMapping("/deletePhoto")
    public String deleteGet(@RequestParam String path) {
        deleteService.deleteGet(path);

        return "redirect:/gallery";
    }

}
