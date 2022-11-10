package com.gospel.backend.pojo.friend;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 好友请求表
 * @author: lzp
 * @created: 2022/11/10 19:21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userFrom;//发送方
    private Integer userTo;//接收方
    private Integer status;//状态 0还没同意 1同意了 2拒绝了
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date sendTime;//发送时间
}
