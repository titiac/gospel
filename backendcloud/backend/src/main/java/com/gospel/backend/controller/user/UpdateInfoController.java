package com.gospel.backend.controller.user;

import com.gospel.backend.service.user.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateInfoController {
    @Autowired
    private UpdateInfoService updateInfoService;

    @PostMapping("/updateUserInfo")
    public Map<String ,String > updateInfo(@RequestBody Map<String ,String > data){
        return updateInfoService.updateInfo(data);
    }
}
