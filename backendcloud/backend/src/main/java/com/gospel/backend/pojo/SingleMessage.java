package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: gospel
 * @description: 私聊消息
 * @author: zhw
 * @created: 2022/11/05 20:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("single_message")
public class SingleMessage {
    private Integer id;
    
    private Integer userFrom;       // 谁发送的
    private Integer userTo;         // 发给谁的
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date timeInfo;          // 发送时间
    private String fileRawName; // 文件的源名称
    private String message;     // 消息、 文件路径
    private String messageType; // 消息类型: emoji/text/img/file/video/audio
    private Integer isRead;     // 消息是否已读的, 0 未读， 1已读
}

