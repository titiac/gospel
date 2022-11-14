package com.gospel.backend.controller.friend;

import com.gospel.backend.common.R;
import com.gospel.backend.service.friend.GetSelfRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetSelfRequestController {
    @Autowired
    private GetSelfRequestService getSelfRequestService;

    @GetMapping("/friend/getSelfRequest")
    public R getSelfRequest(){
        return getSelfRequestService.getSelfRequest();
    }

}
