package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 获取加群请求的id和操作参数
 * @author: zhw
 * @created: 2022/11/14 23:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DealGroupRequestVo {
    private Integer requestListId;
    private Integer operate;        // 0 未处理， 1同意， 2拒绝
}

