package com.gospel.backend.service.impl.user;

import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lzp
 * @Description: 更新用户密码
 * @DateTime: 2022/11/3 12:36
 */

@Service
public class UpdatePasswordServiceImpl implements UpdatePasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> updatePassword(Map<String ,String > data) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();


        String oldPassword=data.get("oldPassword");
        String newPassword=data.get("newPassword");
        String confirmedPassword=data.get("confirmedPassword");
        Map<String ,String > map=new HashMap<>();

        if(oldPassword==null||oldPassword.length()==0){
            map.put("error_message","请输入原密码");
            return map;
        }

        if(newPassword==null||newPassword.length()==0){
            map.put("error_message","请输入新密码");
            return map;
        }

        if(confirmedPassword==null||confirmedPassword.length()==0){
            map.put("error_message","请再次输入新密码");
            return map;
        }

        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            map.put("error_message","原密码不正确");
            return map;
        }

        if(newPassword.length()>15){
            map.put("error_message","新密码长度不应超过15位");
            return map;
        }

        if(!newPassword.equals(confirmedPassword)){
            map.put("error_message","输入新密码不一致");
            return map;
        }

        String encodedPassword=passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userMapper.updateById(user);
        map.put("error_message","success");

        return map;
    }
}
