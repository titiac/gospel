package com.gospel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.FzuGroupMapper;
import com.gospel.backend.mapper.GroupMemberMapper;
import com.gospel.backend.mapper.GroupMessageMapper;
import com.gospel.backend.pojo.FzuGroup;
import com.gospel.backend.pojo.GroupMember;
import com.gospel.backend.pojo.GroupMessage;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.vo.GetMyGroupVo;
import com.gospel.backend.pojo.vo.GroupMessageVo;
import com.gospel.backend.service.GroupService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.utils.GroupMessageChangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: backendcloud
 * @description: 与群相关的服务
 * @author: zhw
 * @created: 2022/11/12 23:01
 */
@Service
public class GroupServiceImpl implements GroupService {
    
    @Autowired
    private GroupMessageMapper groupMessageMapper;
    
    @Autowired
    private GroupMemberMapper groupMemberMapper;
    
    @Autowired
    private FzuGroupMapper fzuGroupMapper;
    
    @Override
    public R getGroupAndMessage() {
        /** 获取用户id */
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer userId1 = user.getId();
        
        /** 存储结果的对象 */
        List<GetMyGroupVo> myGroupVos = new ArrayList<>(); 

        /** 根据用户id 获取 群 list */
     
        QueryWrapper<GroupMember> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id", userId1);
        List<GroupMember> members = groupMemberMapper.selectList(wrapper1);
        ArrayList<Integer> groupIds = new ArrayList<>();
        for(GroupMember member : members){
            groupIds.add(member.getGroupId());            
        }
        
        /** 获取每个群id */
        for(Integer groupId : groupIds){
            /** 存储结果的对象 */
            GetMyGroupVo getMyGroupVo = new GetMyGroupVo();
            
            FzuGroup fzuGroup = fzuGroupMapper.selectById(groupId);
            getMyGroupVo.setFzuGroup(fzuGroup);

            /** 获取消息，此处获取的消息的已读是用set存的 */
            QueryWrapper<GroupMessage> groupMessageQueryWrapper = new QueryWrapper<>();
            groupMessageQueryWrapper.eq("group_id", groupId).orderByDesc("send_time");
            List<GroupMessage> groupMessages = groupMessageMapper.selectList(groupMessageQueryWrapper);

            GroupMessageVo groupMessageVo = null;
            
            if(!groupMessages.isEmpty()) {
                GroupMessage groupMessage = groupMessages.get(0);
                groupMessageVo = GroupMessageChangeUtil.GroupMessageChangeIntoVo(groupMessage, userId1);
            }
            getMyGroupVo.setGroupMessageVo(groupMessageVo);
            myGroupVos.add(getMyGroupVo);
        }
        
        return R.ok().data("myGroup", myGroupVos);
    }
}

