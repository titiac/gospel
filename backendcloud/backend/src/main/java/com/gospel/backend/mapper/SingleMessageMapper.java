package com.gospel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gospel.backend.pojo.SingleMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingleMessageMapper extends BaseMapper<SingleMessage> {
}
