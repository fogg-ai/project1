package org.itstep.controller;

import org.itstep.service.uploadPhoto.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeletePhotoController {
    final
    DeleteService deleteService;

    public DeletePhotoController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @GetMapping("/deletePhoto")
    public String deleteGet(@RequestParam String path) {
        deleteService.deleteGet(path);

        deleteService.deleteOpenPhoto(path);
        return "redirect:/gallery";
    }
}
