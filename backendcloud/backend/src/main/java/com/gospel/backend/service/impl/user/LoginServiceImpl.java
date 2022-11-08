package com.gospel.backend.service.impl.user;

import com.gospel.backend.common.R;
import com.gospel.backend.common.ResultEnum;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.LoginService;
import com.gospel.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lzp
 * @Description: 登录
 * @DateTime: 2022/11/1
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public R getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);

        Authentication authenticate=authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser=(UserDetailsImpl) authenticate.getPrincipal();
        User user=loginUser.getUser();

        String jwt= JwtUtil.createJWT(user.getId().toString());

        return R.ok().resultEnum(ResultEnum.LOGIN_SUCCESS)
                .data("userInfo",user)
                .data("token",jwt);
    }
}
