package com.gospel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.SingleMessageMapper;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.mapper.friend.FriendMapper;
import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.friend.Friend;
import com.gospel.backend.pojo.vo.CreateTemporaryVo;
import com.gospel.backend.pojo.vo.GetFriendAndMessageVo;
import com.gospel.backend.service.FriendService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: backendcloud
 * @description: 好友相关的服务
 * @author: zhw
 * @created: 2022/11/13 22:14
 */

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private SingleMessageMapper singleMessageMapper;

    @Override
    public R getMyFriendAndLatestMessage() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer myselfId = user.getId();

        List<GetFriendAndMessageVo> getFriendAndMessageVos = new ArrayList<>();

        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_from", myselfId);
        List<Friend> friends = friendMapper.selectList(wrapper);
        System.out.println(friends);
        
        for(Friend friend: friends) {
            Integer friendId = friend.getFriendId();
            User friendUser = userMapper.selectById(friendId);

            QueryWrapper<SingleMessage> queryWrapper = new QueryWrapper<>();
            queryWrapper.nested(i -> i.eq("user_from", myselfId).eq("user_to", friendId))
                    .or(i -> i.eq("user_from", friendId).eq("user_to", myselfId))
                    .orderByDesc("send_time");
            List<SingleMessage> singleMessages = singleMessageMapper.selectList(queryWrapper);
            
            SingleMessage singleMessage = null;
            if(!singleMessages.isEmpty()) {
                singleMessage = singleMessages.get(0);
            }

            GetFriendAndMessageVo getFriendAndMessageVo = new GetFriendAndMessageVo(
                    friendUser,
                    singleMessage,
                    friend.getCreateTime(),
                    friend.getFriendType()
            );

            getFriendAndMessageVos.add(getFriendAndMessageVo);
        }
        return R.ok().data("FriendsAndLastMessage", getFriendAndMessageVos);
    }

    @Override
    public R createTemporary(CreateTemporaryVo createTemporaryVo) {
        Integer userFrom = createTemporaryVo.getUserFrom();
        Integer userTo = createTemporaryVo.getUserTo();
        Date now = new Date();
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_from", userFrom)
                .and(i -> i.eq("friend_id", userTo));
        Friend oldFriend = friendMapper.selectOne(wrapper);
        if(oldFriend != null) {
            return R.error();
        }
        
        Friend friendA = new Friend(null, userFrom, userTo, now, 0);
        Friend friendB = new Friend(null, userTo, userFrom, now, 0);
        
        friendMapper.insert(friendA);
        friendMapper.insert(friendB);
        
        return R.ok();
    }
}

