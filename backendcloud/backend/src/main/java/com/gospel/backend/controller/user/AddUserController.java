package com.gospel.backend.controller.user;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.AdminAddUserVo;
import com.gospel.backend.service.user.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: gospel
 * @description: 管理员添加用户
 * @author: zhw
 * @created: 2022/11/03 22:23
 */

@RestController
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    /**
     * @Author: zhw
     * @Description: 管理员添加单个用户
     * @DateTime: 2022/11/3 22:32
     */
    @PostMapping("/user/add/one")
    public R addOne(@RequestBody AdminAddUserVo adminAddUserVo) {
        return addUserService.addOne(adminAddUserVo);
    }
    
    
}

