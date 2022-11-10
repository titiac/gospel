package com.gospel.backend.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: backendcloud
 * @description: 发送出去和接收的群聊消息
 * @author: zhw
 * @created: 2022/11/10 16:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageVo {
    private Integer id;
    private Integer userFrom;   //  谁发送的
    private Integer groupId;    //  发送到的群
    private String senderNickname;// 发送者昵称
    private String senderPhoto; // 发送者头像
    private Date sendTime;      //  发送时间
    private String fileRawName; //  文件原名称
    private String messageType; //  消息类型： emoji/text/img/file/video
    private String message;     //  消息
    private Integer isRead;      //  0 未读， 1 已读
}

