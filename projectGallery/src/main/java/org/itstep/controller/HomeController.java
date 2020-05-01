package org.itstep.controller;

import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.service.UserGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private final UserGalleryService userGalleryService;

    @Autowired
    public HomeController(UserGalleryService userGalleryService) {
        this.userGalleryService = userGalleryService;
    }


    @GetMapping(path="/")
    public String index(){
        return "index";
    }
    @GetMapping(path="/login")
    public String loginP(Model model){
        model.addAttribute("usergallerydto",new UserGalleryDto());
        return "gallery";

    }

    @PostMapping(path="/login")
    public String login(@Validated @ModelAttribute UserGalleryDto userGalleryDto, BindingResult bindingResult, Model model){

        return "gallery";
    }

    @PostMapping(path="/register")
    public String register(@Validated @ModelAttribute UserGalleryDto userGalleryDto, BindingResult bindingResult){
        userGalleryService.save(userGalleryDto);
        return "gallery";

    }
    @GetMapping(path="/register")
    public String registerG(Model model){
        model.addAttribute("usergallerydto",new UserGalleryDto());
        System.out.println(model.toString() + "^^^");
        return "gallery";
    }
}
