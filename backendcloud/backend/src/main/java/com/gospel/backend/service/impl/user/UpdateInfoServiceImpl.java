package com.gospel.backend.service.impl.user;

import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lzp
 * @Description: 更新用户信息
 * @DateTime: 2022/11/4 19:29
 */

@Service
public class UpdateInfoServiceImpl implements UpdateInfoService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> updateInfo(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        String profile=data.get("profile");
        Map<String ,String > map=new HashMap<>();
        if(profile==null||profile.length()==0){
            map.put("error_message","请输入用户简介");
            return map;
        }

        if(profile.length()>10000){
            map.put("error_message","用户简介长度不得多于10000字");
            return map;
        }

        user.setProfile(profile);
        userMapper.updateById(user);
        map.put("error_message","success");

        return map;

    }
}
