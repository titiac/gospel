package com.gospel.backend.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 发送好友请求的参数
 * @author: lzp
 * @created: 2022/11/13 13:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendRequestVo {
    private Integer userFrom;
    private Integer userTo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", timezone = "Asia/Shanghai")
    private Date sendTime;

}
