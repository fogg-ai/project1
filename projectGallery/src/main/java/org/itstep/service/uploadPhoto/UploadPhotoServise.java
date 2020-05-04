package org.itstep.service.uploadPhoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadPhotoServise {
    final
    ResourceLoader resourceLoader;

    public UploadPhotoServise(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void uploadFile(MultipartFile file, String path){
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File j = new File(path + File.separator + file.getOriginalFilename());
        try {
            file.transferTo(j);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
