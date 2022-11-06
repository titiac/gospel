package com.gospel.backend.controller.group;

import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.service.mesage.SingleMessageService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: gospel
 * @description: 获取群成员类
 * @author: zhw
 * @created: 2022/11/06 14:52
 */

@RestController
public class GetMemberController {
    
    @Autowired
    private SingleMessageService singleMessageService;
    
    @GetMapping("/user/single/getMessage")
    public List<SingleMessage> getSingleMessage(@RequestParam("userFrom") String userFrom,
                                                @RequestParam("userTo") String userTo) {
        Integer from = Integer.parseInt(userFrom);
        Integer to = Integer.parseInt(userTo);
        return singleMessageService.getSingleMessage(from, to);
    }
}

