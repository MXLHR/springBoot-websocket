package com.xianlei.springBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/*
 * 1.通过注解开启使用STOMP协议来传输基于代理（message broker）的消息. 
 *   这时控制器支持使用@MessageMapping， 就像使用@RequestMapping一样
 * 2.注册STOMP协议的节点 endpoint，并映射指定的URL
 * 3.注册一个STOMP 的 endpoint，并指定使用SockJS协议
 * 4.配置消息代理MessageBroker
 * 5.广播式应配置一个/topic消息代理
 */
@Configuration
@EnableWebSocketMessageBroker//1
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {//2 注册STOMP协议的节点 endpoint，并映射指定的URL
		registry.addEndpoint("/endpointUser").withSockJS();//3 注册一个STOMP 的 endpoint，并指定使用SockJS协议
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {//4 配置消息代理MessageBroker
		registry.enableSimpleBroker("/topic");//5 广播式应配置一个/topic消息代理
	}

}
