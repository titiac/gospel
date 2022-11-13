package com.gospel.backend.controller;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.AddCourseVo;
import com.gospel.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: backendcloud
 * @description: 与选课和建群相关的接口
 * @author: zhw
 * @created: 2022/11/12 11:09
 */

@RestController
@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    /**
     * @Author: zhw
     * @Description: 管理员添加课程
     * @DateTime: 2022/11/12 15:21
     */
    @PostMapping("/add")
    public R addCourse(@RequestBody AddCourseVo addCourseVo) {
        return courseService.adminAddCourse(addCourseVo);
    }
    
    
    /**
     * @Author: zhw
     * @Description: 学生选课
     * @DateTime: 2022/11/12 15:21
     */
    @PostMapping("/student/select")
    public R studentSelect(@RequestParam String courseId) {
        return courseService.studentSelect(courseId);
    }
    
    
    /**
     * @Author: zhw
     * @Description: 管理员结束选课自动建群
     * @DateTime: 2022/11/12 15:22
     */
    @PostMapping("/endSelect")
    public R endSelect(@RequestParam String courseId) {
        return courseService.endSelect(courseId);
    }
    
    /**
     * @Author: zhw
     * @Description: 管理员获取所有选课信息
     * @DateTime: 2022/11/13 15:01
     */
    @GetMapping("/getAll")                 
    public R adminGetCourse(){
        return courseService.getAllCourse();
    }
    
    /**
     * @Author: zhw
     * @Description: 学生获取所有正在选课的信息
     * @DateTime: 2022/11/13 15:55
     */
    @GetMapping("/student/getCourse")
    public R studentGetCourse(){
        return courseService.studentGet();
    }
        
}

