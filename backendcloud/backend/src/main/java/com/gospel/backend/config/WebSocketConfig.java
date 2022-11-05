package com.gospel.backend.config;

/**
 * @program: gospel
 * @description: WebSocket工具类 调用 ServerEndpointExporter 需要用到
 * @author: zhw
 * @created: 2022/11/05 11:07
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }
}