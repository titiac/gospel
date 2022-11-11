package com.gospel.backend.service.user;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.AdminAddUserVo;


public interface AddUserService {
    R addOne(AdminAddUserVo adminAddUserVo);
}
