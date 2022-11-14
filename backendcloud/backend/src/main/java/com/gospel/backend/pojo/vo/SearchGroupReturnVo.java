package com.gospel.backend.pojo.vo;

import com.gospel.backend.pojo.FzuGroup;
import com.gospel.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: backendcloud
 * @description: 查询群的返回对象
 * @author: zhw
 * @created: 2022/11/14 11:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchGroupReturnVo {
    private FzuGroup fzuGroup;
    private List<User> adminList;
    private Integer membersNum;
}

