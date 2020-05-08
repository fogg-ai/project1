package org.itstep.service.uploadPhoto;

import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.itstep.service.dto.UserGalleryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindDirectoryPhoto {
    final
    UserGalleryRepository userGalleryRepository;

    public FindDirectoryPhoto(UserGalleryRepository userGalleryRepository) {
        this.userGalleryRepository = userGalleryRepository;
    }

    public List<String> findPath(){
        UserGallery user = (UserGallery) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getLogin();
        UserGallery userByLogin = userGalleryRepository.findUserByLogin(name);
        List<File> collect = null;
        List<String> finalPath = null;
        File file = new File(userByLogin.getPhoto().getPath());
        if(file.exists()) {
            try {
                collect = Files.walk(Paths.get(userByLogin.getPhoto().getPath()))
                        .filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finalPath = collect.stream()
                    .map(a ->userByLogin.getPhoto().getPathUrl() + "/" + a)
                    .collect(Collectors.toList());
        }
        return finalPath;
    }
}
