package com.gospel.backend.service.impl.friend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.friend.FriendRequestMapper;
import com.gospel.backend.pojo.friend.FriendRequest;
import com.gospel.backend.pojo.vo.SendRequestVo;
import com.gospel.backend.service.friend.SendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lzp
 * @Description: 发送好友请求
 * @DateTime: 2022/11/12 09:44
 */

@Service
public class SendRequestServiceImpl implements SendRequestService {
    @Autowired
    private FriendRequestMapper friendRequestMapper;

    @Override
    public R sendRequest(SendRequestVo sendRequestVo) {
        if(sendRequestVo.getUserFrom()==null||sendRequestVo.getUserTo()==null||sendRequestVo.getSendTime()==null){
            return R.error();
        }

        QueryWrapper<FriendRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_from",sendRequestVo.getUserFrom())
                .eq("user_to",sendRequestVo.getUserTo())
                .eq("status",0);
        if(!friendRequestMapper.selectList(queryWrapper).isEmpty()){
            return R.error();
        }

        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_from",sendRequestVo.getUserFrom())
                .eq("user_to",sendRequestVo.getUserTo())
                .eq("status",1);
        if(!friendRequestMapper.selectList(queryWrapper).isEmpty()){
            return R.error();
        }

        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_from",sendRequestVo.getUserFrom())
                .eq("user_to",sendRequestVo.getUserTo())
                .eq("status",2);
        if(!friendRequestMapper.selectList(queryWrapper).isEmpty()){
            FriendRequest friendRequest=friendRequestMapper.selectOne(queryWrapper);
            friendRequest.setStatus(0);
            friendRequest.setSendTime(sendRequestVo.getSendTime());
            friendRequestMapper.updateById(friendRequest);
            return R.ok();
        }

        FriendRequest friendRequest=new FriendRequest(
                null, sendRequestVo.getUserFrom(), sendRequestVo.getUserTo(), 0,sendRequestVo.getSendTime()
        );
        friendRequestMapper.insert(friendRequest);
        return R.ok();
    }
}
