package com.gospel.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gospel.backend.pojo.GroupMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMemberMapper extends BaseMapper<GroupMember> {
}
