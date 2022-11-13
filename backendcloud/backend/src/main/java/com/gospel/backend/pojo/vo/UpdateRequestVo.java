package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 处理请求的参数
 * @author: lzp
 * @created: 2022/11/13 13:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequestVo {
    private Integer userFrom;
    private Integer flag;//1同意 2拒绝
}
