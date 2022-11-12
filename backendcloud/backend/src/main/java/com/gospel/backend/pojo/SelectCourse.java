package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 学生选课表
 * @author: zhw
 * @created: 2022/11/12 14:39
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectCourse {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer courseId;
    private Integer studentId;
}

