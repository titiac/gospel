package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: backendcloud
 * @description: 消息pojo 包括私聊消息和群消息
 * @author: zhw
 * @created: 2022/11/10 14:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer groupId;    //  群id
    private Integer userFrom;   //  谁发送的
    private Integer userTo;     //  发给谁的， 如果为群消息， 则为空
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", timezone = "Asia/Shanghai")
    private Date sendTime;      //  发送时间
    private String fileRawName; //  文件源名称
    private String messageType; //  消息类型： emoji/text/img/file/video
    private String message;     //  消息
    private String conversationType;        //  区分单聊消息和群聊消息
}
