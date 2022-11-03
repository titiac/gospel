package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String number;
    private String name;
    private String password;
    private Integer flag;       // 用户类型 0 管理员  1 教师   2 学生
    private String photo;
    private String profile;
    private Integer status;     // 登录状态 0 离线  1 在线
}
