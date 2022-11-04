package com.gospel.backend.service.impl.user;

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
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl)authentication.getPrincipal();
        User user=loginUser.getUser();

        Map<String ,String > map=new HashMap<>();
        map.put("error_message","success");
        map.put("id",user.getId().toString());
        map.put("number",user.getNumber());
        map.put("name",user.getName());
        map.put("flag",user.getFlag().toString());
        map.put("photo",user.getPhoto());
        map.put("profile",user.getProfile());
        map.put("status",user.getStatus().toString());


        return map;
    }
}
