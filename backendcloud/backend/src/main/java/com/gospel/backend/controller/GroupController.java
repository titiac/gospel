package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.GetAllMembersVo;
import com.gospel.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/getMyGroup")
    public R getMyGroup(){
        return groupService.getGroupAndMessage();
    }
    
    @PostMapping("/getAllMembers")
    public R getAllMembers(@RequestBody GetAllMembersVo getAllMembersVo){
        return groupService.getAllMembers(getAllMembersVo);
    }
}

