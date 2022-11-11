package com.gospel.backend.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.GroupMessageMapper;
import com.gospel.backend.pojo.GroupMessage;
import com.gospel.backend.pojo.vo.GetGroupMessageVo;
import com.gospel.backend.pojo.vo.GroupMessageVo;
import com.gospel.backend.pojo.vo.IsReadVo;
import com.gospel.backend.service.mesage.GroupMessageService;
import net.sf.jsqlparser.expression.JsonAggregateOnNullType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: backendcloud
 * @description: 群聊消息的相关服务
 * @author: zhw
 * @created: 2022/11/10 22:56
 */
@Service
public class GroupMessageServiceImpl implements GroupMessageService {

    @Autowired
    private GroupMessageMapper groupMessageMapper;
    
    @Override
    public R getGroupMessage(GetGroupMessageVo getGroupMessageVo) {
        QueryWrapper<GroupMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", getGroupMessageVo.getGroupId());
        List<GroupMessage> groupMessages = groupMessageMapper.selectList(queryWrapper);
        
        List<GroupMessageVo> groupMessageVos = new ArrayList<>();
        
        for(GroupMessage groupMessage: groupMessages) {
            Set<Integer> isRead = new HashSet<>();
            String str = groupMessage.getIsRead();
            isRead = JSONObject.parseObject(str, IsReadVo.class).getIsRead();
            
            GroupMessageVo groupMessageVo = new GroupMessageVo();
            
            groupMessageVo.setId(groupMessage.getId());
            groupMessageVo.setUserFrom(groupMessage.getUserFrom());
            groupMessageVo.setGroupId(groupMessage.getGroupId());
            groupMessageVo.setSenderNickname(groupMessage.getSenderNickname());
            groupMessageVo.setSenderPhoto(groupMessage.getSenderPhoto());
            groupMessageVo.setSendTime(groupMessage.getSendTime());
            groupMessageVo.setFileRawName(groupMessage.getFileRawName());
            groupMessageVo.setMessageType(groupMessage.getMessageType());
            groupMessageVo.setMessage(groupMessage.getMessage());
            if(isRead.contains(getGroupMessageVo.getMyselfId())) {
                groupMessageVo.setIsRead(1);      
            } else {
                groupMessageVo.setIsRead(0);
            }
            
            groupMessageVos.add(groupMessageVo);
        }
        
        return R.ok().data("groupMessage", groupMessageVos);
    }
}

