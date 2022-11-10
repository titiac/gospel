package com.gospel.backend.service.impl.friend;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.mapper.friend.FriendRequestMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.friend.FriendRequest;
import com.gospel.backend.service.friend.GetRequestListService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzp
 * @Description: 获取好友请求列表
 * @DateTime: 2022/11/10 19:56
 */

@Service
public class GetRequestListServiceImpl implements GetRequestListService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendRequestMapper friendRequestMapper;

    @Override
    public R getRequestList() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        QueryWrapper<FriendRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_to",user.getId());
        List<FriendRequest> list=friendRequestMapper.selectList(queryWrapper);
        List<JSONObject> list1=new ArrayList<>();
        for(FriendRequest friendRequest:list){
            QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("id",friendRequest.getUserFrom());
            User user1=userMapper.selectOne(queryWrapper1);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",user1.getId().toString());
            jsonObject.put("number",user1.getNumber());
            jsonObject.put("name",user1.getName());
            jsonObject.put("flag",user1.getFlag().toString());
            jsonObject.put("photo",user1.getPhoto());
            jsonObject.put("profile",user1.getProfile());
            jsonObject.put("status",friendRequest.getStatus().toString());
            jsonObject.put("send_time",friendRequest.getSendTime());
            list1.add(jsonObject);
        }
        return R.ok().data("requestList",list1);
    }
}
