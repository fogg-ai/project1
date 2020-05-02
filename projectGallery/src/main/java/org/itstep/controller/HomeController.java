package org.itstep.controller;


import org.itstep.service.dto.UserGalleryDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping(path="/")
    public String index(){
        return "index";
    }

    @GetMapping(path="/gallery")
    public String indexGallery(){
        return "gallery";
    }
}
