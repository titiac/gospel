package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 发送导师请求的参数
 * @author: lzp
 * @created: 2022/11/13 13:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendTutorRequestVo {
    private Integer tutorId;
}
