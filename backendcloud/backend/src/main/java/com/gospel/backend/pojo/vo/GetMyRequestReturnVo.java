package com.gospel.backend.pojo.vo;

import com.gospel.backend.pojo.FzuGroup;
import com.gospel.backend.pojo.GroupEnterRequest;
import com.gospel.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: backendcloud
 * @description: 获取用户自己请求加群的返回对象
 * @author: zhw
 * @created: 2022/11/14 16:48
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetMyRequestReturnVo {
    private GroupEnterRequest groupEnterRequest;       // 加群请求
    private FzuGroup groupInfo;                        // 群信息
    private List<User> adminList;                      // 群中的管理员
    private User dealMan;                              // 处理人的信息
}

