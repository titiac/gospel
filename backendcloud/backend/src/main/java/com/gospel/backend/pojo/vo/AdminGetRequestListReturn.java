package com.gospel.backend.pojo.vo;

import com.gospel.backend.pojo.FzuGroup;
import com.gospel.backend.pojo.GroupEnterRequest;
import com.gospel.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 管理员获取加群请求返回类
 * @author: zhw
 * @created: 2022/11/14 22:42
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminGetRequestListReturn {
    private GroupEnterRequest groupEnterRequest;
    private FzuGroup fzuGroup;
    private User applyUser;
    private User dealUser;
}

