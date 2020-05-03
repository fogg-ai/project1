package org.itstep.controller;


import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.service.UserGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    final
    UserGalleryService userGalleryService;

    public HomeController(UserGalleryService userGalleryService) {
        this.userGalleryService = userGalleryService;
    }


    @GetMapping(path="/")
    public String index(Model model){
        model.addAttribute("userGalleryDto",new UserGalleryDto());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute UserGalleryDto userGalleryDto,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "index";
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userGalleryDto.setPassword(bCryptPasswordEncoder.encode(userGalleryDto.getPassword()));
        userGalleryService.save(userGalleryDto);
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(new UsernamePasswordAuthenticationToken(userGalleryDto,userGalleryDto.getPassword(),
                AuthorityUtils.createAuthorityList(userGalleryDto.getRole())));
        SecurityContextHolder.setContext(emptyContext);
        return "redirect:/gallery";
    }

    @GetMapping(path="/gallery")
    public String indexGallery(){
        return "gallery";
    }
}
