package com.gospel.backend.utils;

import com.alibaba.fastjson.JSONObject;
import com.gospel.backend.pojo.GroupMessage;
import com.gospel.backend.pojo.vo.GroupMessageVo;
import com.gospel.backend.pojo.vo.IsReadVo;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: backendcloud
 * @description: 消息转化
 * @author: zhw
 * @created: 2022/11/13 10:53
 */
public class GroupMessageChangeUtil {
    public static GroupMessageVo GroupMessageChangeIntoVo(GroupMessage groupMessage, Integer myselfId){
        String isReads = groupMessage.getIsRead();
        Set<Integer> isRead = JSONObject.parseObject(isReads, IsReadVo.class).getIsRead();
        int ir;
        if(isRead.contains(myselfId)) {
            ir = 1;
        } else {
            ir = 0;
        }
        
        GroupMessageVo groupMessageVo = new GroupMessageVo(
                groupMessage.getGroupId(),
                groupMessage.getUserFrom(),
                groupMessage.getGroupId(),
                groupMessage.getSenderNickname(),
                groupMessage.getSenderPhoto(),
                groupMessage.getSendTime(),
                groupMessage.getFileRawName(),
                groupMessage.getMessageType(),
                groupMessage.getMessage(),
                ir
        );
        return groupMessageVo;
    }
}

