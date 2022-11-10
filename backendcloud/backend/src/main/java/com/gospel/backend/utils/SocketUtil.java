package com.gospel.backend.utils;

import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.gospel.backend.mapper.MessageMapper;
import com.gospel.backend.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            System.out.println("群聊消息");
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
        } else {
            System.out.println("用户" + userId + "离线");
        }
        messageMapper.insert(message);
    }

    public void sendToAll(Map<String, Object> msg, String sendChannel) {
        if (sockets.isEmpty()){
            return;
        }
        //给在这个频道的每个客户端发消息
        for (Map.Entry<Integer, SocketIOClient> entry : sockets.entrySet()) {
            entry.getValue().sendEvent(sendChannel, msg);
        }
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

