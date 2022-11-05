package com.gospel.backend.component;

import com.alibaba.fastjson2.JSON;
import com.gospel.backend.mapper.SingleMessageMapper;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.pojo.User;
import com.gospel.backend.utils.JwtAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: gospel
 * @description: websocket 服务
 * @author: zhw
 * @created: 2022/11/05 11:11
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    // 线程安全的哈希表
    public static ConcurrentHashMap<Integer, WebSocketServer> sockets = new ConcurrentHashMap<>();
    public static Map<Integer, Session> sessions = new ConcurrentHashMap<>();
    private User user;
    private Session session = null;
    public static UserMapper userMapper;
    public static SingleMessageMapper singleMessageMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }

    @Autowired
    public void setSingleMessageMapper(SingleMessageMapper singleMessageMapper) {
        WebSocketServer.singleMessageMapper = singleMessageMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException{
        // websocket 连接时启动
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);
        log.info("Socket open: "+userId);
        this.session = session;
        WebSocketServer.sockets.put(userId, this);
        WebSocketServer.sessions.put(userId, this.session);
        log.info("Socket total: "+sockets.size());
    }

    @OnClose
    public void onClose() {
        // websocket 连接关闭时触发
        Integer userId = this.user.getId();
        log.info("Socket close: "+ userId);
        WebSocketServer.sockets.remove(userId);
        WebSocketServer.sessions.remove(userId);
        log.info("Socket total: "+userId);
    }

    @OnMessage
    public void onMessage(String data) {
        // 从Client接收消息
        SingleMessage singleMessage = JSON.parseObject(data, SingleMessage.class);
        Integer to = singleMessage.getUserTo();
        
        if(sockets.get(to) != null) {   // 目标用户存在socket，直接发送并存入数据库
            singleMessage.setIsRead(1);
            sockets.get(to).sendMessage(singleMessage.toString());
//            System.out.println(singleMessage.toString());
            singleMessageMapper.insert(singleMessage);
        } else {        // 目标不存在socket, 直接存入数据库
            singleMessage.setIsRead(0);
            singleMessageMapper.insert(singleMessage);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    public void sendMessage(String message) {
        // 从后端向前端发送一个信息, 异步通信需要加锁
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);  // 后端向前端发送消息
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

