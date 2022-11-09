package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 登录信息
 * @author: lzp
 * @created: 2022/11/09 16:06
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private String account;
    private String password;
}
