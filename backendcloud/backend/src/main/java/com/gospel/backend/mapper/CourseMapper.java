package com.gospel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gospel.backend.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
