package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.GetGroupMessageVo;
import com.gospel.backend.service.mesage.GroupMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @Author: zhw
     * @Description: 获取群消息
     * @DateTime: 2022/11/23 18:09
     */
    @GetMapping("/getRecentGroupMessages")
    R getGroupMessage(GetGroupMessageVo getGroupMessageVo){
        return groupMessageService.getGroupMessage(getGroupMessageVo);
    }
    
    /**
     * @Author: zhw
     * @Description: 群消息设置已读
     * @DateTime: 2022/11/23 18:10
     */
    @PostMapping("/setIsRead")
    R setIsRead(@RequestBody GetGroupMessageVo getGroupMessageVo){
        return groupMessageService.setIsRead(getGroupMessageVo);
    }
}

