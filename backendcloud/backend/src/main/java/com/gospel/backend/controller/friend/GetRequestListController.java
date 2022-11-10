package com.gospel.backend.controller.friend;

import com.gospel.backend.common.R;
import com.gospel.backend.mapper.friend.FriendRequestMapper;
import com.gospel.backend.service.friend.GetRequestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRequestListController {
    @Autowired
    private GetRequestListService getRequestListService;

    @GetMapping("/friend/request/list")
    public R getRequestList(){
        return getRequestListService.getRequestList();
    }

}
