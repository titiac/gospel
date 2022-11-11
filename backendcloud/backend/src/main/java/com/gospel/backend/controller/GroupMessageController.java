package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.GetGroupMessageVo;
import com.gospel.backend.service.mesage.GroupMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: backendcloud
 * @description: 与群消息有关的接口
 * @author: zhw
 * @created: 2022/11/10 19:06
 */

@RestController
@RequestMapping("/groupMessage")
public class GroupMessageController {

    @Autowired
    private GroupMessageService groupMessageService;

    @GetMapping("/getRecentGroupMessages")
    R getGroupMessage(GetGroupMessageVo getGroupMessageVo){
        return groupMessageService.getGroupMessage(getGroupMessageVo);
    }
    
    
}

