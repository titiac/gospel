package com.gospel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.common.ResultEnum;
import com.gospel.backend.mapper.*;
import com.gospel.backend.pojo.*;
import com.gospel.backend.pojo.vo.*;
import com.gospel.backend.service.GroupService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.utils.GroupMessageChangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlList;
import java.text.SimpleDateFormat;
import java.util.*;

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
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private GroupEnterRequestMapper groupEnterRequestMapper;
    
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

    @Override
    public R getAllMembers(GetAllMembersVo getAllMembersVo) {
        Integer groupId = getAllMembersVo.getGroupId();
        if(groupId == null) {
            return R.error();
        }
        List<ReturnGroupMembersVo> returnGroupMembersVos = new ArrayList<>();

        QueryWrapper<GroupMember> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId)
                .and(i -> i.eq("member_status", 1));
        
        List<GroupMember> groupMembers = groupMemberMapper.selectList(wrapper);
        for(GroupMember groupMember: groupMembers) {
            Integer userId = groupMember.getUserId();
            User user = userMapper.selectById(userId);
            ReturnGroupMembersVo returnGroupMembersVo = new ReturnGroupMembersVo(
                user.getId(),
                user.getNumber(),
                user.getName(),
                user.getPassword(),
                user.getFlag(),
                user.getPhoto(),
                user.getCollege(),
                user.getMajor(),
                user.getProfile(),
                groupMember.getMemberType(),
                user.getStatus()    
            );
            
            returnGroupMembersVos.add(returnGroupMembersVo);
        }
        
        
        return R.ok().data("AllMembers", returnGroupMembersVos);
    }

    
    @Override
    public R getGroupByNameOrNumber(SearchGroupVo searchGroupVo) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer userId = user.getId();
        
        String keyword = searchGroupVo.getNameOrNumber();

        if(keyword == null || keyword.length() == 0) {
            return R.error().resultEnum(ResultEnum.GROUP_FOUND_ERROR);
        }
        
        QueryWrapper<FzuGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_number", keyword).or().like("group_name", keyword);
        
        List<FzuGroup> groups = fzuGroupMapper.selectList(queryWrapper); 
        List<SearchGroupReturnVo> searchGroupReturnVos = new ArrayList<>();
        for(FzuGroup fzuGroup : groups){
            Integer groupId = fzuGroup.getId();

            QueryWrapper<GroupMember> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("group_id", groupId);
            List<GroupMember> groupMembers = groupMemberMapper.selectList(queryWrapper1);
            Integer groupMemberNum = groupMembers.size();
            
            List<User> adminList = new ArrayList<>();
            for(GroupMember groupMember: groupMembers) {
                if(Objects.equals(groupMember.getMemberType(), "admin")){
                    Integer adminId = groupMember.getUserId();
                    System.out.println(adminId);
                    User admin = userMapper.selectById(adminId);
                    adminList.add(admin);
                }
            }

            QueryWrapper<GroupMember> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("group_id", groupId)
                    .and(i -> i.eq("user_id", userId))
                    .and(i -> i.eq("member_status", 1));
            GroupMember groupMember = groupMemberMapper.selectOne(queryWrapper2);
            String existMe;
            if(groupMember != null) {
                existMe = "yes";
            }else {
                existMe = "no";
            }
             
            searchGroupReturnVos.add(new SearchGroupReturnVo(fzuGroup, adminList, groupMemberNum, existMe)); 
        }
        if(searchGroupReturnVos.size() == 0){
            searchGroupReturnVos = null;
        }
        return R.ok().data("groups", searchGroupReturnVos);
    }

    @Override
    public R createGroup(CreateGroupRequestVo createGroupRequestVo) {
        Integer createUserId = createGroupRequestVo.getCreateUserId();
        String groupName = createGroupRequestVo.getGroupName();
        List<Integer> memberIds = createGroupRequestVo.getMemberIds();
        if(memberIds.size() < 2) {
            return R.error().resultEnum(ResultEnum.GROUP_MEMBERS_LACK);
        }
        
        /** 先生成群号 */
        Date createTime = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        Random r = new Random();
        String groupNumber = "G" + sdf.format(new Date()) + r.nextInt(9);
        String photo = "https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png";
        String profile = "这个群主很懒什么也没留下";
        
        FzuGroup fzuGroup = new FzuGroup(
                null,
                groupNumber,
                groupName,
                photo,
                createTime,
                profile
        );
        fzuGroupMapper.insert(new FzuGroup(null, groupNumber, groupName, photo, createTime, profile));
        
        /** 拿到群id */
        QueryWrapper<FzuGroup> groupQueryWrapper = new QueryWrapper<>();
        groupQueryWrapper.eq("group_number", groupNumber);
        FzuGroup groupCreated = fzuGroupMapper.selectOne(groupQueryWrapper);
        
        /** 添加普通用户 */
        for(Integer i: memberIds) {
            GroupMember groupMember = new GroupMember(
                    null,
                    groupCreated.getId(),
                    i,
                    "common",
                    1
            );
            groupMemberMapper.insert(groupMember);
        }
        
        /** 添加管理员（群主） */
        GroupMember admin = new GroupMember(
                null,
                groupCreated.getId(),
                createUserId,
                "admin",
                1
        );
        groupMemberMapper.insert(admin);

        return R.ok().data("Group", groupCreated);
    }

    @Override
    public R requestEnter(GroupIdVo groupIdVo) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer userId = user.getId();
        
        Integer groupId = groupIdVo.getGroupId();
        if(groupId == null) {
           return R.error().resultEnum(ResultEnum.GROUP_ID_ERROR); 
        }
        
        if(fzuGroupMapper.selectById(groupId) != null) {
            return R.error().resultEnum(ResultEnum.GROUP_NOT_FOUND);
        }
        
        QueryWrapper<GroupMember> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", groupId)
                .and(i -> i.eq("user_id", userId))
                .and(i -> i.eq("member_status", 1));
        GroupMember groupMember = groupMemberMapper.selectOne(wrapper);
        if(groupMember != null) {
            return R.error().resultEnum(ResultEnum.GROUP_MEMBER_EXIST);
        }
        
        /** 之前的请求未被处理 */
        QueryWrapper<GroupEnterRequest> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("group_id", groupId)
                .and(i -> i.eq("user_from", userId))
                .and(i -> i.eq("status", 0));
        GroupEnterRequest find = groupEnterRequestMapper.selectOne(wrapper1);
        
        if(find != null) {
            return R.error().resultEnum(ResultEnum.GROUP_ENTER_REQUEST_EXIST);
        }
        
        /** 如果不在该群或之前的请求已被拒绝，可以发送请求 */
        GroupEnterRequest groupEnterRequest = new GroupEnterRequest(
            null,
            userId,
            groupId,
            0,
            new Date(),
            null,
            null    
        );
        
        groupEnterRequestMapper.insert(groupEnterRequest);

        return R.ok();
    }
}

