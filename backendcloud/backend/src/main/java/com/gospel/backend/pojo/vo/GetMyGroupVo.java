package com.gospel.backend.pojo.vo;

import com.gospel.backend.pojo.FzuGroup;
import com.gospel.backend.pojo.GroupMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: backendcloud
 * @description: 用于封装返回用户的所在的群及最新一条消息
 * @author: zhw
 * @created: 2022/11/12 21:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetMyGroupVo {
    private FzuGroup fzuGroup;
    private GroupMessageVo GroupMessageVo;
}

