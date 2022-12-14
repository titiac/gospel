package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 搜索用户的参数
 * @author: lzp
 * @created: 2022/11/13 13:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserVo {
    private String keyWord;
    
}
