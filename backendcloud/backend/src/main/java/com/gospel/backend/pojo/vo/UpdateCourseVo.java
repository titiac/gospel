package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseVo {
    private Integer id;
    private String courseName;
    private String teacherName;
    private Integer limitNum;       // 课程限选人数
    private String address;         // 上课地址
}
