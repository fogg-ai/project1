package org.itstep.service.servisePhoto;

import org.itstep.domain.Photo;
import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

@Service
public class LittlePhotoService {

    final
    UserGalleryRepository userGalleryRepository;

    public LittlePhotoService(UserGalleryRepository userGalleryRepository) {
        this.userGalleryRepository = userGalleryRepository;
    }

    public boolean crop(String file) {
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(this.getResource(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();


        int targetWidth = (int) (width * 0.6);
        int targetHeight = (int) (height * 0.6);
        int xc = (width - targetWidth) / 2;
        int yc = (height - targetHeight) / 2;


        if (height < 230) {
            BufferedImage croppedImage = originalImage.getSubimage(
                    xc,
                    0,
                    targetWidth,
                    height
            );
            try {
                return ImageIO.write(croppedImage, "jpg", getResourcePathFile(file));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else if (width < 170) {
            BufferedImage croppedImage = originalImage.getSubimage(
                    0,
                    yc,
                    width,
                    targetHeight
            );
            try {
                return ImageIO.write(croppedImage, "jpg", getResourcePathFile(file));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else if (width < height) {
            BufferedImage croppedImage = originalImage.getSubimage(
                    xc,
                    yc,
                    targetWidth,
                    targetHeight
            );
            try {
                return ImageIO.write(croppedImage, "jpg", getResourcePathFile(file));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else if (width > height) {
            BufferedImage croppedImage = originalImage.getSubimage(xc, yc-300, targetWidth, targetHeight);

            try {
                return ImageIO.write(croppedImage, "jpg", getResourcePathFile(file));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private File getResource(String file) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        Photo photoPackage = userByLogin.getPhoto();


        File finalPath = new File(photoPackage.getPath() + "\\" + file);
        return finalPath;
    }

    private File getResourcePathFile(String file) {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        Photo photoPackage = userByLogin.getPhoto();
        File pathDirectory = new File(photoPackage.getMinPhotoPath());
        if (!pathDirectory.exists()) {
            pathDirectory.mkdir();
        }
        File finalPath = new File(photoPackage.getMinPhotoPath() + "\\" + file);

        return finalPath;
    }

    public List<String> findPath() {
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        List<File> collect = null;
        List<String> finalPath = null;
        File file = new File(userByLogin.getPhoto().getMinPhotoPath());
        if (file.exists()) {
            try {
                collect = Files.walk(Paths.get(userByLogin.getPhoto().getMinPhotoPath()))
                        .filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finalPath = collect.stream()
                    .map(a -> userByLogin.getPhoto().getMinPhotoUrl() + "/" + a)
                    .collect(Collectors.toList());
        }
        return finalPath;
    }
}

