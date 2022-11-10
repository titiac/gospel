package com.gospel.backend.service.mesage;


import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.IsReadSingleMessageRequestVo;

public interface SingleMessageService {
    R getSingleMessage(IsReadSingleMessageRequestVo ivo);

    void setIsRead(IsReadSingleMessageRequestVo ivo);
}
