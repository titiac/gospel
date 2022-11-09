package com.gospel.backend.service.impl.user;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lzp
 * @Description: 获取用户自身信息
 * @DateTime: 2022/11/4 10:31
 */

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public R getInfo() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl)authentication.getPrincipal();
        User user=loginUser.getUser();
        
        return R.ok().data("userInfo", user);
    }
}
