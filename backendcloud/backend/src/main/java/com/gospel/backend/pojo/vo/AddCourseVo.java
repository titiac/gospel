package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 管理员添加课程需要的数据
 * @author: zhw,lzp
 * @created: 2022/11/12 10:54
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddCourseVo {
    private String courseName;      // 课程名
    private String teacherName;      // 教师姓名
    private Integer limitNum;       // 课程限选人数
    private String address;         // 上课地址
}

