package com.gospel.backend.service.impl.user;


import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: zhw
 * @Description: 管理员创建用户
 * @DateTime: 2022/11/3 22:14
 */
@Service
public class AddUserServiceImpl implements AddUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> addOne(Map<String, String> data) {
        Map<String, String> rest = new HashMap<>();

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        if(user.getFlag() != 0){
            rest.put("error_message", "您没有权限进行此操作!");
            return rest;
        }

        String name = data.get("name");

        if(data.get("flag") == null || data.get("flag").length() == 0) {
            rest.put("error_message", "请对新增用户添加权限!");
            return rest;
        }

        Integer flag = Integer.parseInt(data.get("flag"));

        if(flag != 1 && flag != 2) {
            rest.put("error_message", "授权错误!");
            return rest;
        }

        if(name == null || name.length() == 0) {
            rest.put("error_message", "姓名不能为空!");
            return rest;
        }

        String photo = "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png";
        String profile = "这个用户很懒，什么也没留下";
        String username = null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        Random r = new Random();

        if(flag == 1) {
            username = "T" + sdf.format(new Date()) + r.nextInt(9);
        }else {
            username = "S" + sdf.format(new Date()) + r.nextInt(9);
        }

        String password = passwordEncoder.encode("123456");

        User newUser = new User(null, username, name, password, flag, photo, profile, 0);
        userMapper.insert(newUser);

        rest.put("username", username);
        rest.put("error_message", "success");
        return rest;
    }
}
