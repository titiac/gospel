package com.gospel.backend.pojo.tutor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 导师申请表
 * @author: lzp
 * @created: 2022/11/13 13:48
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorRequest {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;   //学生id
    private Integer tutorId;   //导师id
    private Integer status;    //0未处理 1同意 2拒绝
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date sendTime;    //时间
}
