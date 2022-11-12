package com.gospel.backend.service.friend;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.SendRequestVo;

public interface SendRequestService {
    public R sendRequest(SendRequestVo sendRequestVo);
}
