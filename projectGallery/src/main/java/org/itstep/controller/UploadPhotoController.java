package org.itstep.controller;

import org.itstep.repository.PhotoRepository;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.servisePhoto.UploadPhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadPhotoController {

    final
    UploadPhotoService uploadPhotoService;

    final
    UserGalleryRepository userGalleryRepository;

    final
    PhotoRepository photoRepository;


    public UploadPhotoController(UploadPhotoService uploadPhotoService, UserGalleryRepository userGalleryRepository, PhotoRepository photoRepository) {
        this.uploadPhotoService = uploadPhotoService;
        this.userGalleryRepository = userGalleryRepository;
        this.photoRepository = photoRepository;

    }

    @PostMapping("/upload")
    public String upload(@RequestParam("photo") MultipartFile f, Model model) {
       uploadPhotoService.operationFile(f);
        return "redirect:/gallery";
    }


    @GetMapping("/upload")
    public String uploadGet(Model model) {
        return "gallery";
    }

}
