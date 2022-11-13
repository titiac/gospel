package com.gospel.backend.pojo.tutor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 导师学生表
 * @author: lzp
 * @created: 2022/11/13 13:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer tutorId;   //导师id
    private Integer studentId;   //学生id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;    //时间
}
