package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: backendcloud
 * @description: 获取专业相关的接口
 * @author: zhw
 * @created: 2022/11/11 16:02
 */
@RestController
public class MajorController {
    
    @Autowired
    private MajorService majorService;
    
    @GetMapping("/getCollege")
    public R getCollege(){
        return majorService.getCollege();
    }
    
    @GetMapping("/getMajor")
    public R getMajor(String college){
        return majorService.getMajorByCollege(college);
    }
}

