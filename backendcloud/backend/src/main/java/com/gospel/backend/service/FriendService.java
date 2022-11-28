package com.gospel.backend.service;


import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.CreateTemporaryVo;

public interface FriendService {
    R getMyFriendAndLatestMessage();

    R createTemporary(CreateTemporaryVo createTemporaryVo);
}
