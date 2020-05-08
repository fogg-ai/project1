package org.itstep.controller;



import org.apache.commons.math3.util.Precision;
import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.UserGalleryService;
import org.itstep.service.uploadPhoto.FindDirectoryPhoto;
import org.itstep.service.uploadPhoto.UploadPhotoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    final
    UserGalleryService userGalleryService;

    final
    FindDirectoryPhoto findDirectoryPhoto;

    final
    UserGalleryRepository userGalleryRepository;
    final
    UploadPhotoService uploadPhotoServise;

    public HomeController(UserGalleryService userGalleryService, UserGalleryRepository userGalleryRepository,
                          FindDirectoryPhoto findDirectoryPhoto, UploadPhotoService uploadPhotoServise) {
        this.userGalleryService = userGalleryService;
        this.userGalleryRepository = userGalleryRepository;
        this.findDirectoryPhoto = findDirectoryPhoto;
        this.uploadPhotoServise = uploadPhotoServise;
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("userGalleryDto",new UserGalleryDto());
        return "index";
    }

    @GetMapping("/photo/{packagePhoto}")
    public String viewPhoto(@PathVariable String packagePhoto){
        UserGalleryDto user = (UserGalleryDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        System.out.println(packagePhoto);

        return "redirect:/gallery";
    }


    @GetMapping(path="/gallery")
    public String indexGallery(Model model){
        UserGalleryDto user = (UserGalleryDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        model.addAttribute("message", uploadPhotoServise.getCheck());
        model.addAttribute("size",
                Precision.round(userGalleryRepository.findUserByLogin(name).getPhoto().getSize() * 0.0000010,
                        3));
        model.addAttribute("pathList",findDirectoryPhoto.findPath());
        return "gallery";
    }
}
