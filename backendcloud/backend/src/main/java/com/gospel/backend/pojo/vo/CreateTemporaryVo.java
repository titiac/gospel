package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gospel
 * @description: 创建临时会话所需参数
 * @author: zhw
 * @created: 2022/11/28 20:13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTemporaryVo {
    private Integer userFrom;
    private Integer userTo;
}

