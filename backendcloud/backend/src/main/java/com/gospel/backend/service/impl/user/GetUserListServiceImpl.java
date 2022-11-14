package com.gospel.backend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.user.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserListServiceImpl implements GetUserListService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R getUserList() {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("flag",0);
        List<User> list=userMapper.selectList(queryWrapper);
        return R.ok().data("userList",list);
    }
}
