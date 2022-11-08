package com.gospel.backend.controller.user;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login/")
    public R getToken(@RequestBody User user){
        String username=user.getName();
        String password=user.getPassword();
        return loginService.getToken(username,password);
    }

}
