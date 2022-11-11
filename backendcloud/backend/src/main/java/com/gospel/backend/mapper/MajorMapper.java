package com.gospel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gospel.backend.pojo.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    List<String> getCollege();
    
    List<String> getMajor(String college);
}
