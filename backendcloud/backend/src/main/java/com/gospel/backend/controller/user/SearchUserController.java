package com.gospel.backend.controller.user;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.SearchTeacherVo;
import com.gospel.backend.service.user.SearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchUserController {
    @Autowired
    private SearchUserService searchUserService;

    @GetMapping("/user/search/{keyWord}")
    public R searchUser(@PathVariable String keyWord){
        return searchUserService.searchUser(keyWord);
    }
    
    @GetMapping("/user/searchTeacher")
    public R searchTeacher(@RequestParam String college){
        return searchUserService.searchTeacherByCollege(college);
    }
}
