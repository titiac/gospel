package com.gospel.backend.pojo.friend;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 好友表
 * @author: lzp
 * @created: 2022/11/09 21:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userFrom;   //谁的好友
    private Integer friendId;   //好友id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;    //建立关系时间
    private Integer friendType;     // 1 是好友 0 是临时会话
}
