package org.itstep.controller;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.RoleRepository;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.servisePhoto.UploadPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UploadPhotoController {

    final
    UploadPhotoService uploadPhotoService;

    final
    UserGalleryRepository userGalleryRepository;

    final
    PhotoRepository photoRepository;

    final
    RoleRepository roleRepository;

    public UploadPhotoController(UploadPhotoService uploadPhotoService, UserGalleryRepository userGalleryRepository, PhotoRepository photoRepository, RoleRepository roleRepository) {
        this.uploadPhotoService = uploadPhotoService;
        this.userGalleryRepository = userGalleryRepository;
        this.photoRepository = photoRepository;
        this.roleRepository = roleRepository;
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

    @GetMapping("/buyplace")
    public String morePlace(){
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);

        photoRepository.setMaxSize(2000000000,userByLogin.getPhoto().getId());
        roleRepository.setNewRole("ROLE_SUPERUSER",userByLogin.getRolePerson().getId());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), AuthorityUtils.createAuthorityList("ROLE_SUPERUSER"));
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/gallery";
    }

}
