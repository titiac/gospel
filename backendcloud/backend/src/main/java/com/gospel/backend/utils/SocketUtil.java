package com.gospel.backend.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.gospel.backend.mapper.GroupMemberMapper;
import com.gospel.backend.mapper.MessageMapper;
import com.gospel.backend.pojo.Group;
import com.gospel.backend.pojo.GroupMember;
import com.gospel.backend.pojo.Message;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @program: backendcloud
 * @description:
 * @author: zhw
 * @created: 2022/11/09 22:55
 */
@Component
public class SocketUtil {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /** 暂且把用户&客户端信息存在缓存, 线程安全的hash表 */
    public static ConcurrentMap<Integer, SocketIOClient> sockets = new ConcurrentHashMap<>();
    
    @Autowired
    private MessageMapper messageMapper;
    
    @Autowired
    private GroupMemberMapper groupMemberMapper;
    
    /**
     * 监听频道为CHANNEL_SYSTEM的消息
     */
    @OnEvent(value = "CHANNEL_SYSTEM")
    public void systemDataListener(String data) {
        Message message = JSONObject.parseObject(data, Message.class);
        String conversationType = message.getConversationType();
        System.out.println(conversationType);
        if(Objects.equals(conversationType, "single")) { // 如果为私聊消息
            Integer userTo = message.getUserTo();
            sendToOne(userTo, message, "CHANNEL_SYSTEM");
        } else if(Objects.equals(conversationType, "group")) {  // 如果为私聊消息
            Integer GroupId = message.getGroupId();
            sendToGroup(GroupId, message, "CHANNEL_SYSTEM");
        }
    }
    
    /**
     *  私聊消息的发送
     */
    public void sendToOne(Integer userId, Message message,String sendChannel) {
        //拿出某个客户端信息
        SocketIOClient socketClient = getSocketClient(userId);
        message.setId(null);
        if (Objects.nonNull(socketClient) ){ // 如果在线
            //单独给他发消息
            socketClient.sendEvent(sendChannel, message);
        }
        messageMapper.insert(message);
    }
    
    /**
     * 群聊消息的发送 
     */
    public void sendToGroup(Integer groupId, Message message, String sendChannel) {
        message.setId(null);
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId).and(w -> w.eq("member_status", 1));
        List<GroupMember> groupMembers = groupMemberMapper.selectList(queryWrapper);
        for(GroupMember groupMember: groupMembers) {
            Integer userId = groupMember.getUserId();
            SocketIOClient socketIOClient = getSocketClient(userId);
            if(Objects.nonNull(socketIOClient)) {
                socketIOClient.sendEvent(sendChannel, message);
            }
        }
        messageMapper.insert(message);
    }
    
    /**
     * 识别出客户端
     * @return
     */
    public static SocketIOClient getSocketClient(Integer userId){
        SocketIOClient client = null;
        if (!sockets.isEmpty()){
            for (Integer key : sockets.keySet()) {
                if (Objects.equals(userId, key)){
                    client = sockets.get(key);
                }
            }
        }
        return client;
    }
}

