package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 发送已读消息请求
 * @author: zhw
 * @created: 2022/11/10 21:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IsReadSingleMessageRequestVo {
    private Integer myselfId;
    private Integer friendId;
}

