package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 通过学院专业查找老师
 * @author: zhw
 * @created: 2022/11/11 20:25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchTeacherVo {
    private String college;
    private String major;
}

