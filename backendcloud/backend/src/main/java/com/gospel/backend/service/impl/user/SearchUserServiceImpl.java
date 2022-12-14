package com.gospel.backend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.common.ResultEnum;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.vo.SearchTeacherVo;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.SearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzp
 * @Description: 搜索用户
 * @DateTime: 2022/11/10 22:44
 */

@Service
public class SearchUserServiceImpl implements SearchUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R searchUser(String keyWord) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("number",keyWord).or().eq("name",keyWord);
        List<User> list=userMapper.selectList(queryWrapper);
        if(list.isEmpty()){
            return R.error().resultEnum(ResultEnum.USER_NOT_FOUND);
        }

        return R.ok().data("user_list",list);
    }

    @Override
    public R searchTeacherByMajor() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("major", user.getMajor()).and(i -> i.eq("flag", 1));
        
        List<User> teachers= userMapper.selectList(queryWrapper);
        
        return R.ok().data("teachers", teachers);
    }
}
