package com.gospel.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 课程
 * @author: zhw
 * @created: 2022/11/11 20:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private String courseName;      // 课程名
    private Integer teacherId;      // 教师用户id
    private String groupId;         // 课程群号
    private Integer status;         // 状态   0 结束选课, 1 开启选课
}

