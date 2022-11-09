package com.gospel.backend.controller.friend;

import com.gospel.backend.common.R;
import com.gospel.backend.service.friend.GetFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetFriendListController {
    @Autowired
    private GetFriendListService getFriendListService;

    @GetMapping("/friend/list")
    public R getFriendList(){
        return getFriendListService.getFriendList();
    }

}
