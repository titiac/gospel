package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 学院专业表  
 * @author: zhw
 * @created: 2022/11/11 15:26
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Major {
    @TableId(type = IdType.AUTO)
    private Integer id;     
    private String college;     // 学院
    private String major;       // 专业
}