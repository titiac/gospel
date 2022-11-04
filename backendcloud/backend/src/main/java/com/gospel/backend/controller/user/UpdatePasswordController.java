package com.gospel.backend.controller.user;

import com.gospel.backend.service.user.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdatePasswordController {
    @Autowired
    private UpdatePasswordService updatePasswordService;

    @PostMapping("/user/password/update/")
    public Map<String ,String > updatePassword(@RequestParam Map<String ,String > data){
        return updatePasswordService.updatePassword(data);
    }

}
