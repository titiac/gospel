package com.gospel.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: backendcloud
 * @description: 发送加群请求表
 * @author: zhw
 * @created: 2022/11/14 15:37
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupEnterRequest {
    private Integer id;
    private Integer userFrom;       // 谁发来的加群请求
    private Integer groupId;        // 想要加入的那个群的群id
    private Integer status;         // 请求处理状态   0 未处理; 1 已处理; 2 已拒绝;
    private Date sendTime;          // 请求发送的时间
    private Date dealTime;          // 请求被处理的时间
    private Integer dealAdminId;    // 请求处理人的id
}

