package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 获取群聊消息的Vo类
 * @author: zhw
 * @created: 2022/11/10 23:00
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetGroupMessageVo {
    private Integer groupId;
    private Integer myselfId;
}

