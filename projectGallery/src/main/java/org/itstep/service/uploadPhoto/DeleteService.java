package org.itstep.service.uploadPhoto;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Service
public class DeleteService {
    final
    UserGalleryRepository userGalleryRepository;

    final
    PhotoRepository photoRepository;

    public DeleteService(UserGalleryRepository userGalleryRepository, PhotoRepository photoRepository) {
        this.userGalleryRepository = userGalleryRepository;
        this.photoRepository = photoRepository;
    }

    public boolean deleteGet(String path) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        Photo photoPackage = userByLogin.getPhoto();

        int i = path.lastIndexOf('/');
        File file = new File(userByLogin.getPhoto().getPath() + path.substring(i));

        if(file.exists()) {
            photoPackage.setSize(photoPackage.getSize() - file.length());
            photoRepository.setUserInfoById(photoPackage.getSize(), userByLogin.getPhoto().getId());
            file.delete();
            return true;
        }else {
            return false;
        }

    }
}
