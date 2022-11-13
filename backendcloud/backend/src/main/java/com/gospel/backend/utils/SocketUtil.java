package com.gospel.backend.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.gospel.backend.mapper.GroupMemberMapper;
import com.gospel.backend.mapper.GroupMessageMapper;
import com.gospel.backend.mapper.SingleMessageMapper;
import com.gospel.backend.pojo.GroupMember;
import com.gospel.backend.pojo.GroupMessage;
import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.pojo.vo.GroupMessageVo;
import com.gospel.backend.pojo.vo.IsReadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

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
    private SingleMessageMapper singleMessageMapper;
    
    @Autowired
    private GroupMessageMapper groupMessageMapper;
    
    @Autowired
    private GroupMemberMapper groupMemberMapper;
    
    /**
     * 监听频道为Single_Message的消息
     */
    @OnEvent(value = "Single_Message")
    public void listenSingleMessage(String data) throws InterruptedException {
        SingleMessage singleMessage = JSONObject.parseObject(data, SingleMessage.class);
        Integer userTo = singleMessage.getUserTo();
        Integer userFrom = singleMessage.getUserFrom();
        sendToOne(userTo, singleMessage, "Single_Message");
        sendToOne(userFrom, singleMessage, "Single_Message");
    }
    
    /**
     *  监听 群聊消息
     */
    @OnEvent(value = "Group_Message")
    public void listenGroupMessage(String data) {
        GroupMessageVo groupMessageVo = JSONObject.parseObject(data, GroupMessageVo.class);
        Integer groupId = groupMessageVo.getGroupId();
        sendToGroup(groupId, groupMessageVo, "Group_Message");
    }
    
    /**
     *  私聊消息的发送
     */
    public void sendToOne(Integer userId, SingleMessage singleMessage,String sendChannel) throws InterruptedException {
        //拿出某个客户端信息
        SocketIOClient socketClient = getSocketClient(userId);
        singleMessage.setId(null);
        singleMessage.setIsRead(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        singleMessage.setSendTime(new Date());
        if (Objects.nonNull(socketClient) ){ // 如果在线
            socketClient.sendEvent(sendChannel, singleMessage);
            System.out.println(singleMessage);
            TimeUnit.MILLISECONDS.sleep(200);
            singleMessage.setIsRead(1);
        }
        if(userId == singleMessage.getUserTo())
            singleMessageMapper.insert(singleMessage);
    }
    
    /**
     * 群聊消息的发送 
     */
    public void sendToGroup(Integer groupId, GroupMessageVo groupMessageVo, String sendChannel) {
        groupMessageVo.setId(null);
        groupMessageVo.setIsRead(0);
        /** 查询群聊中的人 */
        QueryWrapper<GroupMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId).and(w -> w.eq("member_status", 1));
        List<GroupMember> groupMembers = groupMemberMapper.selectList(queryWrapper);


        IsReadVo isReadVo = new IsReadVo();     /** 用于存储已读的人的id */
        Set<Integer> isRead = new HashSet<>();
        
        for(GroupMember groupMember: groupMembers) {
            Integer userId = groupMember.getUserId();
            SocketIOClient socketIOClient = getSocketClient(userId);
            if(Objects.nonNull(socketIOClient)) {
                isRead.add(userId);
                groupMessageVo.setIsRead(0);
                socketIOClient.sendEvent(sendChannel, groupMessageVo);
            }
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        isReadVo.setIsRead(isRead);
        JSONObject json = (JSONObject) JSONObject.toJSON(isReadVo);
        GroupMessage groupMessage = new GroupMessage(
                null,
                groupMessageVo.getUserFrom(),
                groupMessageVo.getGroupId(),
                groupMessageVo.getSenderNickname(),
                groupMessageVo.getSenderPhoto(),
                new Date(),
                groupMessageVo.getFileRawName(),
                groupMessageVo.getMessageType(),
                groupMessageVo.getMessage(),
                json.toString()
        );
        groupMessageMapper.insert(groupMessage);
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

