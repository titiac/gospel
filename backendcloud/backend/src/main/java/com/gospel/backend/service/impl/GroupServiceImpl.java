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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
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
            QueryWrapper<FzuGroup> wrapper = new QueryWrapper<>();
            wrapper.eq("id", groupId)
                    .and(i -> i.eq("status", 1));
            FzuGroup fzuGroup = fzuGroupMapper.selectOne(wrapper);
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
            if(getMyGroupVo.getFzuGroup() != null)
                myGroupVos.add(getMyGroupVo);
        }
        
        return R.ok().data("myGroup", myGroupVos);
    }

    @Override
    public R getAllMembers(GetAllMembersVo getAllMembersVo) {
        Integer groupId = getAllMembersVo.getGroupId();
        if(groupId == null) {
            return R.error().resultEnum(ResultEnum.GROUP_ID_ERROR);
        }
        
        if(fzuGroupMapper.selectById(groupId).getStatus() == 0) {
            return R.error().resultEnum(ResultEnum.GROUP_IS_DROP);
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
                groupMember.getMemberStatus()
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
        queryWrapper.eq("status", 1)
                .and(i -> i.eq("group_number", keyword).or().like("group_name", keyword));        
        
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
                profile,
                1,
                null
        );
        fzuGroupMapper.insert(fzuGroup);
        
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
        
        if(fzuGroupMapper.selectById(groupId) == null) {
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

    @Override
    public R getMyRequest() {
        List<GetMyRequestReturnVo> getMyRequestReturnVos = new ArrayList<>();
        
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer myselfId = user.getId();
        
        // 获取请求列表
        QueryWrapper<GroupEnterRequest> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_from", myselfId)
                .orderByDesc("send_time");
        List<GroupEnterRequest> groupEnterRequests = groupEnterRequestMapper.selectList(wrapper1);
        
        for(GroupEnterRequest groupEnterRequest : groupEnterRequests) {
            // 获取群信息
            Integer groupId = groupEnterRequest.getGroupId();
            /** 有个问题，如果请求在，然后群被解散了, 信息全部返回 */
            FzuGroup fzuGroup = fzuGroupMapper.selectById(groupId);
            
            // 获取群中管理员的信息
            QueryWrapper<GroupMember> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("group_id", groupId)
                    .and(i -> i.eq("member_type", "admin"))
                    .and(i -> i.eq("member_status", 1));
            List<GroupMember> groupMembers = groupMemberMapper.selectList(wrapper2);
            
            List<User> admins = new ArrayList<>();
            for(GroupMember groupMember : groupMembers) {
                admins.add(userMapper.selectById(groupMember.getUserId()));
            }
            User dealMan = null;
            if(groupEnterRequest.getDealAdminId() != null){
                dealMan = userMapper.selectById(groupEnterRequest.getDealAdminId());
            }
            getMyRequestReturnVos.add(new GetMyRequestReturnVo(groupEnterRequest, fzuGroup, admins, dealMan));
        }

        return R.ok().data("myRequestList", getMyRequestReturnVos);
    }

    @Override
    public R inviteMembers(InviteOrDeleteMembersVo inviteOrDeleteMembersVo) {
        List<GetMyRequestReturnVo> getMyRequestReturnVos = new ArrayList<>();

        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer myselfId = user.getId();
                
        Integer groupId = inviteOrDeleteMembersVo.getGroupId();
        if(groupId == null) {
            return R.error().resultEnum(ResultEnum.GROUP_FOUND_ERROR);
        }
        
        /** 如果这个用户在群里 */
        QueryWrapper<GroupMember> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("group_id", groupId)
                .and(i -> i.eq("user_id", myselfId))
                .and(i -> i.eq("member_status", 1));
        
        GroupMember inviter = groupMemberMapper.selectOne(wrapper1);
        if(inviter == null) {
            return R.error().resultEnum(ResultEnum.ILLEGAL_OPERATION);
        }
        
        /** 如果是管理员邀请 */
        List<Integer> list = inviteOrDeleteMembersVo.getMembersId();
        if(Objects.equals(inviter.getMemberType(), "admin")) {
            for (Integer newMemberId : list) {
                /** 需要将之前发送的加群请求全部更新 */
                QueryWrapper<GroupEnterRequest> wrapper5 = new QueryWrapper<>();
                wrapper5.eq("group_id", groupId)
                        .and(i -> i.eq("user_from", newMemberId))
                        .and(i -> i.eq("status", 0));

                GroupEnterRequest oldEnterRequest = groupEnterRequestMapper.selectOne(wrapper5);
                if(oldEnterRequest != null) {
                    oldEnterRequest.setDealAdminId(myselfId);
                    oldEnterRequest.setStatus(1);
                    oldEnterRequest.setDealTime(new Date());
                    groupEnterRequestMapper.updateById(oldEnterRequest);   
                }
                
                // 查询之前在不在群里该用户
                QueryWrapper<GroupMember> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("group_id", groupId)
                        .and(i -> i.eq("user_id", newMemberId));
                GroupMember oldMember = groupMemberMapper.selectOne(wrapper2);
                if(oldMember != null) {
                    if(oldMember.getMemberStatus() == 0) {
                        oldMember.setMemberType("common");
                        oldMember.setMemberStatus(1);
                        groupMemberMapper.updateById(oldMember);
                        log.info("管理员直接将用户{}拉入群聊P{}", newMemberId, groupId);
                    } else {
                        log.info("用户{}已经在群{}中，不做处理", newMemberId, groupId);
                    }
                } else {
                    GroupMember newMember = new GroupMember(
                        null,
                        groupId,
                        newMemberId,
                        "common",
                        1    
                    );
                    groupMemberMapper.insert(newMember);
                }
            }
        } else {
            /** 如果是普通群员邀请 */
            for (Integer newMemberId : list) {
                // 查询之前在不在这个群
                QueryWrapper<GroupMember> wrapper3 = new QueryWrapper<>();
                wrapper3.eq("group_id", groupId)
                        .and(i -> i.eq("user_id", newMemberId));
                GroupMember oldMember = groupMemberMapper.selectOne(wrapper3);
                
                if(oldMember != null) {
                    /** 之前在这个群 */
                    if(oldMember.getMemberStatus() == 0) {
                        /** 被踢出 */
                        GroupEnterRequest newRequest = new GroupEnterRequest(
                                null,
                                newMemberId,
                                groupId,
                                0,
                                new Date(),
                                null,
                                null
                        );
                        groupEnterRequestMapper.insert(newRequest);
                    } else {
                        log.info("用户{}已经在群{}中，不做处理", newMemberId, groupId);
                    }
                } else {
                    /** 查询之前有无加群请求 */
                    QueryWrapper<GroupEnterRequest> wrapper4 = new QueryWrapper<>();
                    wrapper4.eq("group_id", groupId)
                            .and(i -> i.eq("user_from", newMemberId))
                            .and(i -> i.eq("status", 0));
                    
                    GroupEnterRequest oldEnterRequest = groupEnterRequestMapper.selectOne(wrapper4);
                    if(oldEnterRequest != null){
                        /** 存在加群请求还未被处理 */
                        log.info("用户{}已经已经请求加入{}，不做处理", newMemberId, groupId);
                    } else {
                        GroupEnterRequest newRequest = new GroupEnterRequest(
                                null,
                                newMemberId,
                                groupId,
                                0,
                                new Date(),
                                null,
                                null
                        );
                        groupEnterRequestMapper.insert(newRequest);
                    }
                    
                }
            }
        }
        return R.ok();
    }

    @Override
    public R getRequestList() {
        /** 获取自己管理的群 */
        List<GetMyRequestReturnVo> getMyRequestReturnVos = new ArrayList<>();

        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        Integer myselfId = user.getId();

        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", myselfId)
                .and(i -> i.eq("member_type", "admin"))
                .and(i -> i.eq("member_status", 1));
        
        List<GroupMember> groupMembers = groupMemberMapper.selectList(queryWrapper);
        List<AdminGetRequestListReturn> adminGetRequestListReturns = new ArrayList<>();
        
        for(GroupMember groupMember : groupMembers) {
            
            Integer groupId = groupMember.getGroupId();
            FzuGroup fzuGroup = fzuGroupMapper.selectById(groupId);
            
            /** 获取对应群的请求 */
            QueryWrapper<GroupEnterRequest> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("group_id", groupId)
                    .orderByAsc("status")
                    .orderByAsc("send_time");
            
            List<GroupEnterRequest> groupEnterRequests = groupEnterRequestMapper.selectList(queryWrapper1);

            for(GroupEnterRequest groupEnterRequest : groupEnterRequests) {
                AdminGetRequestListReturn adminGetRequestListReturn = new AdminGetRequestListReturn();
                adminGetRequestListReturn.setFzuGroup(fzuGroup);
                adminGetRequestListReturn.setGroupEnterRequest(groupEnterRequest);
                User applyUser = userMapper.selectById(groupEnterRequest.getUserFrom());
                adminGetRequestListReturn.setApplyUser(applyUser);
                if(groupEnterRequest.getDealAdminId() != null) {
                    adminGetRequestListReturn.setDealUser( 
                            userMapper.selectById(groupEnterRequest.getDealAdminId())
                    );
                } 
                
                adminGetRequestListReturns.add(adminGetRequestListReturn);
            }
        }
        return R.ok().data("RequestList", adminGetRequestListReturns);
    }
}

