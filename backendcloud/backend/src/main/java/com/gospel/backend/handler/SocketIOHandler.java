package com.gospel.backend.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.gospel.backend.utils.JwtAuthentication;
import com.gospel.backend.utils.SocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * @program: backendcloud
 * @description: SocketIoHandler
 * @author: zhw
 * @created: 2022/11/09 22:29
 */
@Component
public class SocketIOHandler{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SocketIOServer socketIoServer;
    @PostConstruct
    private void start(){
        try {
            socketIoServer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @PreDestroy
    private void destroy(){
        try {
            socketIoServer.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @OnConnect
    public void connect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("token");
        Integer userId = JwtAuthentication.getUserId(token);
        SocketUtil.sockets.put(userId, client);
        System.out.println(SocketUtil.sockets);
        log.info("用户id 为{}的用户已上线", userId);
    }
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("token");
        Integer userId = JwtAuthentication.getUserId(token);
        SocketUtil.sockets.remove(userId);
        System.out.println(SocketUtil.sockets);
        log.info("用户id 为{}的用户已下线", userId);
    }
}

