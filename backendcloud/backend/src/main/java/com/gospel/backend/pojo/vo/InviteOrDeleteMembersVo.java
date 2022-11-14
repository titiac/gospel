package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: backendcloud
 * @description: 邀请或删除群成员对象
 * @author: zhw
 * @created: 2022/11/14 14:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InviteOrDeleteMembersVo {
    private Integer groupId;            // 要加的群或者要处理的群成员对象
    private List<Integer> membersId;    // 要踢出的人或邀请的人的Id
}