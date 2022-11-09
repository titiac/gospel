package com.gospel.backend.service.impl.friend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.mapper.friend.FriendMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.friend.Friend;
import com.gospel.backend.service.friend.GetFriendListService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzp
 * @Description: 获取好友列表
 * @DateTime: 2022/11/9 21:42
 */

@Service
public class GetFriendListServiceImpl implements GetFriendListService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public R getFriendList() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        QueryWrapper<Friend> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_from",user.getId());
        List<Friend> list=friendMapper.selectList(queryWrapper);

        List<User> list1=new ArrayList<>();
        for(Friend friend:list){
            QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("id",friend.getFriendId());
            User user1=userMapper.selectOne(queryWrapper1);
            list1.add(user1);
        }
        return R.ok().data("friendList",list1);

    }
}
