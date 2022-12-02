package com.gospel.backend.service.impl.friend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.friend.FriendMapper;
import com.gospel.backend.mapper.friend.FriendRequestMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.friend.Friend;
import com.gospel.backend.pojo.friend.FriendRequest;
import com.gospel.backend.pojo.vo.UpdateRequestVo;
import com.gospel.backend.service.friend.UpdateRequestService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: lzp
 * @Description: 处理好友申请
 * @DateTime: 2022/11/12 17:21
 */

@Service
public class UpdateRequestServiceImpl implements UpdateRequestService {

    @Autowired
    private FriendRequestMapper friendRequestMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public R updateRequest(UpdateRequestVo updateRequestVo) {
        // TODO: 需要查询之前是不是有临时会话
        /* 获取用户 */
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        /* 更新请求表 */
        QueryWrapper<FriendRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_to",user.getId()).eq("user_from",updateRequestVo.getUserFrom());
        FriendRequest friendRequest=friendRequestMapper.selectOne(queryWrapper);
        friendRequest.setStatus(updateRequestVo.getFlag());
        friendRequestMapper.updateById(friendRequest);

        if(updateRequestVo.getFlag()==1){
            /* 同意 */
            Integer userFrom = user.getId();
            Integer userTo = updateRequestVo.getUserFrom();
            QueryWrapper<Friend> wrapper = new QueryWrapper<>();
            wrapper.eq("user_from", userFrom)
                    .and(i -> i.eq("user_to", userTo));
            
            Date date=new Date();
            Friend friend1 = friendMapper.selectOne(wrapper);
            if(friend1 != null) {
                friend1.setFriendType(1);
                friend1.setCreateTime(date);
                friendMapper.updateById(friend1);
                
                wrapper = new QueryWrapper<>();
                wrapper.eq("user_from", userTo)
                        .and(i -> i.eq("user_to", userFrom));
                Friend friend2 = friendMapper.selectOne(wrapper);
                friend2.setFriendType(1);
                friend2.setCreateTime(date);
                return R.ok().data("result","成功添加好友");
            } else {
                Friend friend=new Friend(null, user.getId(), updateRequestVo.getUserFrom(), date, 1);
                friendMapper.insert(friend);
                friend=new Friend(null,updateRequestVo.getUserFrom(),user.getId(),date, 1);
                friendMapper.insert(friend);
                return R.ok().data("result","成功添加好友"); 
            }
        }
        if(updateRequestVo.getFlag()==2){
            return R.ok().data("result","成功拒绝对方添加自己为好友");
        }

        return R.error();
    }
}
