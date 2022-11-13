package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 返回给前端的群成员
 * @author: zhw
 * @created: 2022/11/13 23:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnGroupMembersVo {
    private Integer id;
    private String number;      // 账号
    private String name;        // 名字
    private String password;    // 密码
    private Integer flag;       // 用户类型 0 管理员  1 教师   2 学生
    private String photo;       // 头像
    private String college;     // 学院
    private String major;       // 专业
    private String profile;     // 简介
    private String member_type; // 用户在群聊中的类型    admin/common
    private Integer status;     // 登录状态 0 离线  1 在线
}

