package org.itstep.controller;



import org.apache.commons.math3.util.Precision;
import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.UserGalleryService;
import org.itstep.service.servisePhoto.FindDirectoryPhotoService;
import org.itstep.service.servisePhoto.UploadPhotoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    final
    UserGalleryService userGalleryService;

    final
    FindDirectoryPhotoService findDirectoryPhotoService;

    final
    UserGalleryRepository userGalleryRepository;
    final
    UploadPhotoService uploadPhotoService;

    public HomeController(UserGalleryService userGalleryService, UserGalleryRepository userGalleryRepository,
                          FindDirectoryPhotoService findDirectoryPhotoService, UploadPhotoService uploadPhotoService) {
        this.userGalleryService = userGalleryService;
        this.userGalleryRepository = userGalleryRepository;
        this.findDirectoryPhotoService = findDirectoryPhotoService;
        this.uploadPhotoService = uploadPhotoService;
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("userGalleryDto",new UserGalleryDto());

        return "index";
    }

    @GetMapping("/photo/{packagePhoto}")
    public String viewPhoto(@PathVariable String packagePhoto){
        return "redirect:/gallery";
    }



    @GetMapping(path="/gallery")
    public String indexGallery(@RequestParam(required = false) String path , Model model){
        UserGallery principal = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getLogin();
        model.addAttribute("message", uploadPhotoService.getCheck());
        model.addAttribute("size",
                Precision.round(userGalleryRepository.findUserByLogin(name).getPhoto().getSize() * 0.000001,
                        1));
        model.addAttribute("sizeMax",
                Precision.round(userGalleryRepository.findUserByLogin(name).getPhoto().getMaxSize() * 0.000001,
                        0));
        model.addAttribute("pathList", findDirectoryPhotoService.findPath());
        if(path != null) {
            uploadPhotoService.openSourcePhoto(path);
            model.addAttribute("pathOpenPhoto", findDirectoryPhotoService.findOpenPath(path));
        }else {
            model.addAttribute("pathOpenPhoto", "");
        }
        return "gallery";
    }
}
