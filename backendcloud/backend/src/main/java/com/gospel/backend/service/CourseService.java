package com.gospel.backend.service;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.AddCourseVo;
import com.gospel.backend.pojo.vo.UpdateCourseVo;

public interface CourseService {
    R adminAddCourse(AddCourseVo addCourseVo);

    R studentSelect(String courseId);

    R endSelect(String courseId);

    R adminGetCourse(Integer type);

    R studentGet();

    R adminGetRecord();

    R updateCourse(UpdateCourseVo updateCourseVo);

    R studentGetSelf();
    
}
