package com.gospel.backend.service.mesage;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.GetGroupMessageVo;

public interface GroupMessageService{
    
    R getGroupMessage(GetGroupMessageVo getGroupMessageVo);
    
    R setIsRead(GetGroupMessageVo getGroupMessageVo);
}
