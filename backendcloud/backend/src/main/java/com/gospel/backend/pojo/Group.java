package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gospel
 * @description: 群表
 * @author: zhw
 * @created: 2022/11/06 11:33
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String groupName;       // 群名称
    private String photo;           // 群头像
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private String createTime;      // 群创建时间
    private String profile;         // 群简介
}