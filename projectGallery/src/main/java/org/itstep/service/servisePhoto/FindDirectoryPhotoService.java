package org.itstep.service.servisePhoto;

import org.itstep.domain.UserGallery;
import org.itstep.repository.UserGalleryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindDirectoryPhotoService {
    final
    UserGalleryRepository userGalleryRepository;

    public FindDirectoryPhotoService(UserGalleryRepository userGalleryRepository) {
        this.userGalleryRepository = userGalleryRepository;
    }

    public String findOpenPath(String pathPersonPhoto){
        List<String> path = findPath();
        Optional<String> first = Optional.of(path.stream().filter(s -> s.equals(pathPersonPhoto))
                .map(s -> s.substring(6)).map(s -> "/openphoto" + s).findFirst()
                .orElse("There is no such photo"));
        return first.get();
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
