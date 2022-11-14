package com.gospel.backend.service;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.CreateGroupRequestVo;
import com.gospel.backend.pojo.vo.GetAllMembersVo;
import com.gospel.backend.pojo.vo.GroupIdVo;
import com.gospel.backend.pojo.vo.SearchGroupVo;

public interface GroupService {
    R getGroupAndMessage();

    R getAllMembers(GetAllMembersVo getAllMembersVo);

    R getGroupByNameOrNumber(SearchGroupVo searchGroupVo);

    R createGroup(CreateGroupRequestVo createGroupRequestVo);

    R requestEnter(GroupIdVo groupIdVo);
}
