package com.gospel.backend.controller.user;

import com.gospel.backend.common.R;
import com.gospel.backend.service.user.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserListController {
    @Autowired
    private GetUserListService getUserListService;

    @GetMapping("/user/getList")
    public R getUserList(){
        return getUserListService.getUserList();
    }

}
