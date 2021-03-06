package org.itstep.service.servisePhoto;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public boolean deleteOpenPhoto(String path) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);

        int i = path.lastIndexOf('/');
        File file = new File("src/main/webapp/openPhoto/"
                + userByLogin.getLogin() + File.separator + path.substring(i));


        System.out.println(file);
        if(file.exists()) {

            file.delete();
            return true;
        }else {
            return false;
        }
    }
    public boolean minPhoto(String path) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        Photo photoPackage = userByLogin.getPhoto();

        int i = path.lastIndexOf('/');
        File file = new File(userByLogin.getPhoto().getMinPhotoPath() + path.substring(i));

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
