package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 查找群的所需要的信息
 * @author: zhw
 * @created: 2022/11/14 10:59
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchGroupVo {
    private String nameOrNumber;
}

