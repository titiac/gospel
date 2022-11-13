package com.gospel.backend.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: backendcloud
 * @description: 获取好友列表同时获取最新一条消息
 * @author: zhw
 * @created: 2022/11/13 22:51
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetFriendAndMessageVo {
    private User friend;
    private SingleMessage singleMessage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", timezone = "Asia/Shanghai")
    private Date beginTime;
}

