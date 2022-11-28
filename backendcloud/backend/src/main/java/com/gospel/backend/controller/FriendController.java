package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.CreateTemporaryVo;
import com.gospel.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: backendcloud
 * @description: 与好友有关的接口
 * @author: zhw
 * @created: 2022/11/13 22:07
 */

@RestController
@RequestMapping("/friend")
public class FriendController {
    
    @Autowired
    private FriendService friendService;
    
    @GetMapping("/getFriendAndMessage")
    public R getFriendAndLastMessage(){
        return friendService.getMyFriendAndLatestMessage();
    }
    
    @PostMapping("/createTemporary")
    public R createTemporary(@RequestBody CreateTemporaryVo createTemporaryVo){
        return friendService.createTemporary(createTemporaryVo);
    }
}

