package org.itstep.service.servisePhoto;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class UploadPhotoService {
    private String check;

    final
    UserGalleryRepository userGalleryRepository;
    final
    PhotoRepository photoRepository;

    final
    ResourceLoader resourceLoader;

    final
    LittlePhotoService littlePhotoService;
    public UploadPhotoService(ResourceLoader resourceLoader, UserGalleryRepository userGalleryRepository, PhotoRepository photoRepository, LittlePhotoService littlePhotoService) {
        this.resourceLoader = resourceLoader;
        this.userGalleryRepository = userGalleryRepository;
        this.photoRepository = photoRepository;
        this.littlePhotoService = littlePhotoService;
    }

    public String getCheck() {
        return check;
    }

    public boolean uploadFile(List<MultipartFile> file, String path, long size, long maxSize) {
        check = "";
        boolean c = false;
        if (size <= maxSize) {
            for (MultipartFile fill : file) {
                if (fill.getContentType().equals("image/jpeg") || fill.getContentType().equals("image/png") ||
                        fill.getContentType().equals("video/mpeg")) {
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    File j = new File(path + File.separator + fill.getOriginalFilename());

                    try {
                        fill.transferTo(j);
                        c = true;
                        check = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    c = false;
                    check = "Type file not can save check file(png,jpeg,mpeg)";
                }
            }
        } else{
            c = false;
            check = "Not enough storage";
        }

        return c;
    }

    public void openSourcePhoto(String path) {

        UserGallery user =
                (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        int i = path.lastIndexOf('/');
        File file = new File(userByLogin.getPhoto().getPath() + path.substring(i));
        File copyPath = new File("src/main/webapp/openPhoto/" + userByLogin.getLogin() + File.separator);

        if (!copyPath.exists()) {
            copyPath.mkdirs();
        }
        File finalPath = new File(copyPath.getPath() + path.substring(i));
        try {
            if (file.exists()) {
                Files.copy(file.toPath(), finalPath.toPath());
            }
        } catch (Exception e) {

        }

    }

    public void operationFile(List<MultipartFile> f) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        Photo photoPackage = userByLogin.getPhoto();

        boolean b = this.uploadFile(f, photoPackage.getPath(), photoPackage.getSize(), photoPackage.getMaxSize());
        if (b) {
            for (MultipartFile fill : f) {
                littlePhotoService.crop(fill.getOriginalFilename());
                photoPackage.setSize(photoPackage.getSize() + fill.getSize());
                photoRepository.setUserInfoById(photoPackage.getSize(), userByLogin.getPhoto().getId());
            }

        }
    }
}
