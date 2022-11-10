package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.IsReadSingleMessageRequestVo;
import com.gospel.backend.service.mesage.SingleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @program: backendcloud
 * @description: 与私聊消息有关的接口
 * @author: zhw
 * @created: 2022/11/10 19:07
 */

@RestController
@RequestMapping("/singleMessage")
public class SingleMessageController {
    
    @Autowired
    private SingleMessageService singleMessageService;


    /**
     * 获取最近的单聊消息
     */
    @PostMapping("/getRecentSingleMessages")
    public R getRecentSingleMessages(@RequestBody IsReadSingleMessageRequestVo iVo) {
        return singleMessageService.getSingleMessage(iVo);
    }

    /**
     * 当用户在切换会话阅读消息后，标记该消息已读
     */
    @PostMapping("/isRead")
    public R userIsReadMessage(@RequestBody IsReadSingleMessageRequestVo iVo) {
        singleMessageService.setIsRead(iVo);
        System.out.println(iVo);
        return R.ok();
    }
}

