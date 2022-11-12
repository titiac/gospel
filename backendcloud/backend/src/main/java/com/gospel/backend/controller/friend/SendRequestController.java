package com.gospel.backend.controller.friend;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.SendRequestVo;
import com.gospel.backend.service.friend.SendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendRequestController {
    @Autowired
    private SendRequestService sendRequestService;

    @PostMapping("/friend/send")
    public R sendRequest(@RequestBody SendRequestVo sendRequestVo){
        return sendRequestService.sendRequest(sendRequestVo);
    }

}
