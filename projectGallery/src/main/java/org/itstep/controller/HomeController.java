package org.itstep.controller;


import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.UserGalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    final
    UserGalleryService userGalleryService;

    public HomeController(UserGalleryService userGalleryService) {
        this.userGalleryService = userGalleryService;
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("userGalleryDto",new UserGalleryDto());
        return "index";
    }



    @GetMapping(path="/gallery")
    public String indexGallery(){
        return "gallery";
    }
}
