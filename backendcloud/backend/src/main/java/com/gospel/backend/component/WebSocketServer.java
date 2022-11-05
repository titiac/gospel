package com.gospel.backend.component;

import com.alibaba.fastjson.JSONObject;
import com.gospel.backend.mapper.UserMapper;
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

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException{
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
        Integer userId = this.user.getId();
        log.info("Socket close: "+ userId);
        WebSocketServer.sockets.remove(userId);
        WebSocketServer.sessions.remove(userId);
        log.info("Socket total: "+userId);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        JSONObject data = JSONObject.parseObject(message);
        Integer to = Integer.parseInt(data.getString("to"));
        Integer from = this.user.getId();
        String msg = data.getString("msg");
        if(userMapper.selectById(to) != null) {
            sockets.get(to).sendMessage(msg);
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

