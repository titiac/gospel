package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: backendcloud
 * @description: 群聊消息
 * @author: zhw
 * @created: 2022/11/10 16:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userFrom;   //  谁发送的
    private Integer groupId;    //  发送到的群
    private String senderNickname;// 发送者昵称
    private String senderPhoto; // 发送者头像
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", timezone = "Asia/Shanghai")
    private Date sendTime;      //  发送时间
    private String fileRawName; //  文件原名称
    private String messageType; //  消息类型： emoji/text/img/file/video
    private String message;     //  消息
    private String isRead;      //  存储已读的人的列表
}

