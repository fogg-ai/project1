package org.itstep.controller;


import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.PhotoDto;
import org.itstep.service.dto.RoleDto;
import org.itstep.service.dto.UserGalleryDto;
import org.itstep.service.UserGalleryService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    final
    UserGalleryService userGalleryService;

    final
    UserGalleryRepository userGalleryRepository;

    public RegisterController(UserGalleryService userGalleryService, UserGalleryRepository userGalleryDto) {
        this.userGalleryService = userGalleryService;
        this.userGalleryRepository = userGalleryDto;
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute UserGalleryDto userGalleryDto, PhotoDto photoDto, RoleDto roleDto,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "index";
        }
        roleDto.setRole("ROLE_USER");
        photoDto.setPath("src/main/webapp/photo/" + userGalleryDto.getLogin());
        photoDto.setPathUrl(photoDto.getPath().substring(15));
        photoDto.setSize(0);
        photoDto.setMaxSize(1000000000);
        userGalleryDto.setPhoto(photoDto);
        userGalleryDto.setRolePerson(roleDto);
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userGalleryDto.setPassword(bCryptPasswordEncoder.encode(userGalleryDto.getPassword()));
            userGalleryService.save(userGalleryDto);
        }catch (Exception e){
            userGalleryDto.setPassword("");
            model.addAttribute("errorLogin","This login is already taken");
            return "index";
        }

        UserGallery userByLogin = userGalleryRepository.findUserByLogin(userGalleryDto.getLogin());

        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(new UsernamePasswordAuthenticationToken(userByLogin, userByLogin.getPassword(),
                AuthorityUtils.createAuthorityList(userByLogin.getRolePerson().getRole())));
        SecurityContextHolder.setContext(emptyContext);
        return "redirect:/gallery";
    }
}
