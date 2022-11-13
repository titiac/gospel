package com.gospel.backend.pojo.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 管理员请求课程信息返回类型
 * @author: zhw
 * @created: 2022/11/13 17:36
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminGetCourseVo {
    private Integer id;
    private String courseName;
    private String teacherName;
    private Integer studentNum;
    private Integer limitNum;
    private String groupNumber;   // 群号
    private String address;
    private Integer status;       // 状态, 1开启, 0 关闭    
}

