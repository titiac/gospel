package com.gospel.backend.utils;

import io.jsonwebtoken.Claims;

/**
 * @program: gospel
 * @description: 用于通过token获取用户id
 * @author: zhw
 * @created: 2022/11/05 11:14
 */

public class JwtAuthentication {
    public static Integer getUserId(String token){
        int userId = -1;   // -1 表示找不到
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}