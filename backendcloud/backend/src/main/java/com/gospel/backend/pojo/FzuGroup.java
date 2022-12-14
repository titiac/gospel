package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: gospel
 * @description: 群表
 * @author: zhw
 * @created: 2022/11/06 11:33
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FzuGroup {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String groupNumber;     // 群号
    private String groupName;       // 群名称
    private String photo;           // 群头像
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date groupCreateTime;      // 群创建时间
    private String groupProfile;         // 群简介
    private Integer status;               // 群是否解散 1 正常   0解散
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date dropTime;              // 群聊解散时间
}