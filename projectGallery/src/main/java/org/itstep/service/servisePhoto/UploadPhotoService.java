package org.itstep.service.servisePhoto;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.PhotoRepository;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class UploadPhotoService {
    private String check;

    final
    UserGalleryRepository userGalleryRepository;
    final
    PhotoRepository photoRepository;

    final
    ResourceLoader resourceLoader;

    public UploadPhotoService(ResourceLoader resourceLoader, UserGalleryRepository userGalleryRepository, PhotoRepository photoRepository) {
        this.resourceLoader = resourceLoader;
        this.userGalleryRepository = userGalleryRepository;
        this.photoRepository = photoRepository;
    }

    public String getCheck() {
        return check;
    }

    public boolean uploadFile(MultipartFile file, String path, long size) {
        check = "";
        boolean c = false;
        if (size <= 1000000000) {
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") ||
                    file.getContentType().equals("video/mpeg")) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File j = new File(path + File.separator + file.getOriginalFilename());

                try {
                    file.transferTo(j);
                    c = true;
                    check = "";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                c = false;
                check = "Type file not can save check file(png,jpeg,mpeg)";
            }
        } else {
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

    public void operationFile(MultipartFile f) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        Photo photoPackage = userByLogin.getPhoto();

        boolean b = this.uploadFile(f, photoPackage.getPath(), photoPackage.getSize());
        if (b) {
            photoPackage.setSize(photoPackage.getSize() + f.getSize());
            photoRepository.setUserInfoById(photoPackage.getSize(), userByLogin.getPhoto().getId());
        }
    }
}
