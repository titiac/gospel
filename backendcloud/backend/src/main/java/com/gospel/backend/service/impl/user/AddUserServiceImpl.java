package com.gospel.backend.service.impl.user;


import com.gospel.backend.common.R;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.vo.AdminAddUserVo;
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

import static com.gospel.backend.common.ResultEnum.ILLEGAL_OPERATION;

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
    public R addOne(AdminAddUserVo adminAddUserVo) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        if(user.getFlag() != 0){
            return R.error();
        }

        String name = adminAddUserVo.getName();
        Integer flag = adminAddUserVo.getFlag();
        if(flag == null) {
            return R.error();
        }


        if(flag != 1 && flag != 2) {
            return R.error();
        }

        if(name == null || name.length() == 0) {
            return R.error();
        }

        String photo = "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png";
        String profile ;
        String username ;
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        Random r = new Random();

        if(flag == 1) {
            profile = "https://csip.fzu.edu.cn/?page_id=636";
            username = "T" + sdf.format(new Date()) + r.nextInt(9);
        }else {
            profile = "https://www.cnblogs.com/xuanyuan/";
            username = "S" + sdf.format(new Date()) + r.nextInt(9);
        }

        String password = passwordEncoder.encode("123456");
        String college = adminAddUserVo.getCollege();
        String major = adminAddUserVo.getMajor();
        User newUser = new User(null, username, name, password, flag, photo, college, major, profile, 0);
        userMapper.insert(newUser);
        
        Map<String, String> rest = new HashMap<>();
        rest.put("username", username);
        rest.put("password", "123456");
        
        return R.ok().data("user", rest);
    }
}
