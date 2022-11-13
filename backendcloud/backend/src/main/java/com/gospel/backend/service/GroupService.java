package com.gospel.backend.service;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.GetAllMembersVo;

public interface GroupService {
    R getGroupAndMessage();

    R getAllMembers(GetAllMembersVo getAllMembersVo);
}
