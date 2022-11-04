package com.gospel.backend.controller.user;

import com.gospel.backend.service.user.UpdatePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class UpdatePhotoController {
    @Autowired
    private UpdatePhotoService updatePhotoService;

    @PostMapping("/user/photo/update/")
    public Map<String ,String > updatePhoto(@RequestParam MultipartFile file){
        return updatePhotoService.updatePhoto(file);
    }

}
