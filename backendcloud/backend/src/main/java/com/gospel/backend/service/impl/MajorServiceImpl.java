package com.gospel.backend.service.impl;

import com.gospel.backend.common.R;
import com.gospel.backend.mapper.MajorMapper;
import com.gospel.backend.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: backendcloud
 * @description: 查找专业相关
 * @author: zhw
 * @created: 2022/11/11 15:58
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public R getCollege() {
        return R.ok().data("college", majorMapper.getCollege());
    }

    @Override
    public R getMajorByCollege(String college) {
        return R.ok().data("major", majorMapper.getMajor(college));
    }
    
}

