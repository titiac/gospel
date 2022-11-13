package com.gospel.backend.service;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.AddCourseVo;

public interface CourseService {
    R adminAddCourse(AddCourseVo addCourseVo);

    R studentSelect(String courseId);

    R endSelect(String courseId);

    R adminGetCourse(Integer type);

    R studentGet();
    
}
