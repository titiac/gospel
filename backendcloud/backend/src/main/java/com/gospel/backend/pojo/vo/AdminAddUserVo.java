package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 管理员添加用户所需参数
 * @author: zhw
 * @created: 2022/11/11 15:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminAddUserVo {
    private String name;        // 姓名
    private String college;     // 学院
    private String major;       // 专业
    private Integer flag;       // 权限   用户类型 0 管理员  1 教师   2 学生
}

