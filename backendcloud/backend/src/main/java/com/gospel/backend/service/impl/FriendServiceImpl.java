package com.gospel.backend.service.impl;

import com.gospel.backend.common.R;
import com.gospel.backend.mapper.friend.FriendMapper;
import com.gospel.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: backendcloud
 * @description: 好友相关的服务
 * @author: zhw
 * @created: 2022/11/13 22:14
 */

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public R getMyFriendAndLatestMessage() {
        return null;
    }
}

