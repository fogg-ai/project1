package org.itstep.controller;


import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadPhotoController {

    final
    UserGalleryRepository userGalleryRepository;

    public DownloadPhotoController(UserGalleryRepository userGalleryRepository) {
        this.userGalleryRepository = userGalleryRepository;
    }

    @GetMapping("/download")
    public void getFile(@RequestParam String path, HttpServletResponse response){
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);

        int i = path.lastIndexOf('/');
        File file = new File(userByLogin.getPhoto().getPath() + path.substring(i));

        System.out.println(path.substring(i+1));
        System.out.println(userByLogin.getPhoto().getPath() + path.substring(i+1));
        response.setHeader("Content-disposition", "attachment;filename=" + path.substring(i+1));
            response.setContentType("image/jpg");

            try {
                Files.copy(file.toPath(), response.getOutputStream());
                response.getOutputStream().flush();
            } catch ( IOException e) {
                throw new RuntimeException("IOError writing file to output stream");
            }
    }

}
