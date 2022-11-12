package com.gospel.backend.service.user;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.SearchTeacherVo;

public interface SearchUserService {
    public R searchUser(String keyWord);
    
    public R searchTeacherByCollege(String college);
}
