package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: backendcloud
 * @description: 创建群聊请求的参数
 * @author: zhw
 * @created: 2022/11/14 14:02
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateGroupRequestVo {
    private Integer createUserId;
    private List<Integer>  memberIds;
    private String GroupName;
}

