package org.itstep.controller;


import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.UserGalleryService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    final
    UserGalleryService userGalleryService;

    public RegisterController(UserGalleryService userGalleryService) {
        this.userGalleryService = userGalleryService;
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute UserGalleryDto userGalleryDto,
                           BindingResult bindingResult, Model model) {
        userGalleryDto.setPhotoPackage("src/main/webapp/photo/" + userGalleryDto.getLogin());
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "index";
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userGalleryDto.setPassword(bCryptPasswordEncoder.encode(userGalleryDto.getPassword()));
        try {
            userGalleryService.save(userGalleryDto);
        }catch (Exception e){
            model.addAttribute("errorLogin","Login not unique.Pls come up with a new login");
            return "index";
        }


        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(new UsernamePasswordAuthenticationToken(userGalleryDto,userGalleryDto.getPassword(),
                AuthorityUtils.createAuthorityList(userGalleryDto.getRole())));
        SecurityContextHolder.setContext(emptyContext);
        return "redirect:/gallery";
    }
}
