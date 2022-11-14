package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.*;
import com.gospel.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: backendcloud
 * @description: 与群相关的接口
 * @author: zhw
 * @created: 2022/11/12 22:08
 */
@RequestMapping("/group")
@RestController
public class GroupController {
    
    @Autowired
    private GroupService groupService; 
    
    /**
     * @Author: zhw
     * @Description: 获取群聊同时携带最新一条消息
     * @DateTime: 2022/11/14 13:36
     */
    @GetMapping("/getMyGroup")
    public R getMyGroup(){
        return groupService.getGroupAndMessage();
    }
    
    /**
     * @Author: zhw
     * @Description:  获取群聊中的所有用户
     * @DateTime: 2022/11/14 13:38
     */
    @PostMapping("/getAllMembers")
    public R getAllMembers(@RequestBody GetAllMembersVo getAllMembersVo){
        return groupService.getAllMembers(getAllMembersVo);
    }
    
    /**
     * @Author: zhw
     * @Description: 通过群名和群id查找群
     * @DateTime: 2022/11/14 13:39
     */
    @PostMapping("/search")
    public R getGroupByNameOrNumber(@RequestBody SearchGroupVo searchGroupVo) {
        return groupService.getGroupByNameOrNumber(searchGroupVo);
    }
    
    /**
     * @Author: zhw
     * @Description: 创建群聊
     * @DateTime: 2022/11/14 14:23
     */
    @PostMapping("/create")
    public R createGroup(@RequestBody CreateGroupRequestVo createGroupRequestVo) {
        return groupService.createGroup(createGroupRequestVo);
    }
    
    /**
     * @Author: zhw
     * @Description: 请求加入群聊
     * @DateTime: 2022/11/14 14:23
     */
    @PostMapping("/requestEnter")
    public R requestEnter(@RequestBody GroupIdVo groupIdVo) {
        return groupService.requestEnter(groupIdVo);
    }
    
    /**
     * @Author: zhw
     * @Description: 邀请多个新成员
     * @DateTime: 2022/11/14 14:30
     */
    @PostMapping("/invite")
    public R request(@RequestBody InviteOrDeleteMembersVo inviteOrDeleteMembersVo) {
        /** 如果是管理员邀请 */
        /** 如果是普通用户邀请 */
        return null;
    }
    
    /**
     * @Author: zhw
     * @Description: 获取我发送的请求
     * @DateTime: 2022/11/14 14:23
     */
    @GetMapping("/get/myRequest")
    public R getMyRequest(){
        return null;
    }
    
    /**
     * @Author: zhw
     * @Description: 管理员获取发送过来的加群请求
     * @DateTime: 2022/11/14 14:33
     */
    @GetMapping("/get/requestList")
    public R getRequestList(){
        return null;
    }
    
    /**
     * @Author: zhw
     * @Description: 管理员处理加群请求
     * @DateTime: 2022/11/14 14:30
     */
    @PostMapping("/dealRequest")
    public R dealRequest(@RequestBody GroupRequestIdVo groupRequestIdVo) {
        return null;
    }
    
    
    /**
     * @Author: zhw
     * @Description: 管理员踢出多个群成员
     * @DateTime: 2022/11/14 14:37
     */
    @PostMapping("/deleteMember")
    public R deleteMember(@RequestBody InviteOrDeleteMembersVo inviteOrDeleteMembersVo) {
        return null;
    }
    
    /**
     * @Author: zhw
     * @Description: 管理员删除群聊
     * @DateTime: 2022/11/14 14:39
     */
    @PostMapping("/delete")
    public R deleteGroup(@RequestBody GroupIdVo groupIdVo) {
        return null;
    }
    
    /**
     * @Author: zhw
     * @Description: 普通用户退出群聊
     * @DateTime: 2022/11/14 14:52
     */
    @PostMapping("/out")
    public R OutGroup(@RequestBody GroupIdVo groupIdVo) {
        return null;
    }
}

