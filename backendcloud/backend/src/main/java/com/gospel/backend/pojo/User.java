package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户表
 * @author: lzp,zhw
 * @created: 2022/11/12 xx:xx
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;         
    private String number;      // 账号
    private String name;        // 名字
    private String password;    // 密码
    private Integer flag;       // 用户类型 0 管理员  1 教师   2 学生
    private String photo;       // 头像
    private String college;     // 学院
    private String major;       // 专业
    private String profile;     // 简介
    private Integer status;     // 登录状态 0 离线  1 在线
}
