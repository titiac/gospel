package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 学生获取用户的返回值
 * @author: zhw
 * @created: 2022/11/13 18:05
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentGetCourseVo {
    private Integer id;
    private String courseName;
    private String teacherName;
    private Integer studentNum;
    private Integer limitNum;
    private String groupNumber;   // 群号
    private String address;
    private Integer flag;       // 是否选中     0 未选中, 1 一选中
    private Integer status;     // 是否被关闭选课
}

