package org.itstep.api;


import org.itstep.service.servisePhoto.UploadPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    final
    UploadPhotoService uploadPhotoService;

    public RestController(UploadPhotoService uploadPhotoService) {
        this.uploadPhotoService = uploadPhotoService;
    }


    @PostMapping("/rest/photo")
    public String uploadPhotoRest(@RequestParam("file") List<MultipartFile> f){
        uploadPhotoService.operationFile(f);
        return "";
    }

}
