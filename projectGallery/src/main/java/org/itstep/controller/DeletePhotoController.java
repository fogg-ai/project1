package org.itstep.controller;

import org.itstep.service.servisePhoto.DeleteService;
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

        deleteService.minPhoto(path);
        return "redirect:/gallery";
    }
}
