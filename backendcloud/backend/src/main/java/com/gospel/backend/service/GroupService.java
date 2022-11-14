package com.gospel.backend.service;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.*;

public interface GroupService {
    R getGroupAndMessage();

    R getAllMembers(GetAllMembersVo getAllMembersVo);

    R getGroupByNameOrNumber(SearchGroupVo searchGroupVo);

    R createGroup(CreateGroupRequestVo createGroupRequestVo);

    R requestEnter(GroupIdVo groupIdVo);

    R getMyRequest();

    R inviteMembers(InviteOrDeleteMembersVo inviteOrDeleteMembersVo);

    R getRequestList();
}
