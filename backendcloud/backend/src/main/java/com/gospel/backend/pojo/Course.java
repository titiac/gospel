package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String courseName;      // 课程名
    private Integer teacherId;      // 教师用户id
    private Integer limitNum;       // 课程限选人数
    private Integer groupId;        // 课程群号
    private String address;         // 上课地址
    private Integer status;         // 状态   0 结束选课, 1 开启选课
}

